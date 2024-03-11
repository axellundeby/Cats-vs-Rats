package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.IEntity;

import java.util.EnumMap;

public abstract class Rat implements IEntity {
    private int speed;
    private Vector2 pos;
    private int health;
    private Rectangle spriteRect;
    private float secs;


    private boolean isFrozen;
    public ImageSwapper currentState = ImageSwapper.ALIVE;
    private EnumMap<ImageSwapper, Texture> textures = new EnumMap<>(ImageSwapper.class);

    public Rat(int health, int speed, Texture texture) {
        this.health = health;
        this.speed = speed;
        int halfsize = 25;
        this.pos = new Vector2(-10, 430);

        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y + halfsize, halfsize * 2, halfsize * 2);
        this.secs = 0;
        textures.put(ImageSwapper.ALIVE, texture);
        textures.put(ImageSwapper.FROZEN, new Texture(Gdx.files.internal("snow.png")));
        textures.put(ImageSwapper.DEAD, new Texture(Gdx.files.internal("coin.png")));
    }

    public enum ImageSwapper {
        ALIVE,
        FROZEN,
        DEAD;
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

    public void takeDamage(int damage) {
        health -= damage;
        if (isKilled()) {
            killed();
        }
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
        else throw new Error("Error in Rat movement: Nowhere to go");
    
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
    public void killed() {
        pos.x = -100;
        pos.y = -100;
        speed = 0;
        swapImage(ImageSwapper.DEAD);
    }

    @Override
    public boolean isKilled() {
        return health <= 0;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Vector2 getPosition() {
        return pos;
    }

    public int freeze() {
        isFrozen = true;
        swapImage(ImageSwapper.FROZEN);
        return speed/2;
    }
    public boolean isFrozen() {
        return isFrozen;
    }
}
