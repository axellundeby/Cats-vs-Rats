package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.IEntity;
import inf112.skeleton.app.model.entities.Projectile;
import java.util.ArrayList;
import java.util.EnumMap;

public abstract class Rat implements IEntity {
    private int speed;
    private Vector2 pos;
    private int health;
    private Rectangle spriteRect;
    private float secs;
    private Integer bounty;
    private Integer points;
    private boolean rewardClaimed = false;
    
    
    private boolean isFrozen;
    public ImageSwapper currentState = ImageSwapper.ALIVE;
    private EnumMap<ImageSwapper, Texture> textures = new EnumMap<>(ImageSwapper.class);
    int halfsize = 25;
    

    public Rat(int health, int speed, Texture aliveTexture, Texture deadTexture, Texture frozenTexture, Integer bounty, Integer points) {
        this.health = health;
        this.points = points;
        this.bounty = bounty;
        this.speed = speed;
        this.bounty = bounty;
        this.pos = new Vector2(-10, 430);

        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y + halfsize, halfsize * 2, halfsize * 2);
        this.secs = 0;
        textures.put(ImageSwapper.ALIVE, aliveTexture);
        textures.put(ImageSwapper.FROZEN, frozenTexture);
        textures.put(ImageSwapper.DEAD, deadTexture);
    }

    public boolean isrewardClaimed() {
        return rewardClaimed;
    }

    public void rewardClaimed() {
        this.rewardClaimed = true;
    }

    public void rectangleUpdater(){
        spriteRect.x = pos.x - halfsize;
        spriteRect.y = pos.y - halfsize;
        
    }

    public enum ImageSwapper {
        ALIVE,
        FROZEN,
        DEAD;
    }

    public boolean isHitByProjectile(ArrayList<Projectile> projectiles) {
        for (Projectile rect : projectiles) {
           if (rect.getRectangle().overlaps(spriteRect)) {
                return true;
           }
        }
        return false;
    }

    public Projectile getHitByProjectile(ArrayList<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
           if (projectile.getRectangle().overlaps(spriteRect)) {
                return projectile;
           }
        }
        return null;
    }

    public int getBounty() {
        return bounty;
    }

    public int getPoints() {
        return points;
    }

    public void swapImage(ImageSwapper image) {
        currentState = image; 
    }

    public Texture getTexture() {
        return textures.get(currentState); 
    }

    public Rectangle getRectangle() {
        return spriteRect;
    }

    //må kanskje endre denne, hvis et prosjektil treffer en rotte, så skal den ta skade. Er det berde.
    public void takeDamage(int damage) {
        health -= damage;
    }

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        OUT;
    }

    public void addTime() {
        this.secs += 0.05;
        
    }

    public Direction getDirection() {

        int category;
        if (secs < 4) category = 1;
        else if (secs < 9) category = 2;
        else if (secs < 14) category = 3;
        else if (secs < 24) category = 4;
        else if (secs < 31) category = 5;
        else if (secs < 35) category = 6;
        else if (secs < 51) category = 7;
        else if (secs < 57) category = 8;
        else if (secs < 62) category = 9;
        else if (secs < 67) category = 10;
        else if (secs < 72.5) category = 11;
        else if (secs < 78) category = 12;
        else if (secs < 85) category = 13;
        else if (secs < 87) category = 14;
        else return Direction.OUT;
        
    
        switch (category) {
            case 1:
            case 3:
            case 7:
            case 11:
                return Direction.RIGHT;
            case 2:
            case 8:
            case 10:
            case 12:
            case 14:
                return Direction.UP;
            case 4:
            case 6:
                return Direction.DOWN;
            case 5:
            case 9:
            case 13:
                return Direction.LEFT;
            default:
                throw new AssertionError("Unexpected value: " + category);
        }
    }
    

    @Override
    public void move() {
        switch (getDirection()) {
            case UP:
                pos.y += speed;
                break;
            case DOWN:
                pos.y -= speed;
                break;
            case RIGHT:
                pos.x += speed;
                break;
            case LEFT:
                pos.x -= speed;
                break;
            case OUT:
                // liv -1
            break;
            default:
                throw new Error("Error in class Rat");
        }

        spriteRect.x = pos.x;
        spriteRect.y = pos.y;
    }
    
    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public void killedAnimation() {
        speed = 0;
        swapImage(ImageSwapper.DEAD);
    }

    @Override
    public boolean isKilled() {
        return health <= 0;
    }

    public boolean isOut(){
        if (this.getDirection()==Direction.OUT){
            return true;
        }
        return false;
    }
    public void setPosition(Vector2 pos) {
        this.pos = pos;
    }



    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Vector2 getPosition() {
        return pos;
    }

    public void freeze() {
        isFrozen = true;
        //speed = speed / 2;
        swapImage(ImageSwapper.FROZEN);
    }

    public void unfreeze() {
        isFrozen = false;
        //speed = speed * 2; 
        swapImage(ImageSwapper.ALIVE);
    }

    public boolean isFrozen() {
        return isFrozen;
    }
}
