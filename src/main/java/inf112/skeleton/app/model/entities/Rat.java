package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.Path;
import java.util.EnumMap;

public class Rat implements IEntity {
    private int speed;
    private Vector2 pos;
    private int health;
    private Path path;
    private float distanceTraveled = 0f;
    private Rectangle spriteRect;
    private Texture texture;
    private float secs;
    private int timeAlive;
    private Cat cat;
    private boolean isFrozen;
    public ImageSwapper currentState = ImageSwapper.ALIVE;
    private EnumMap<ImageSwapper, Texture> textures = new EnumMap<>(ImageSwapper.class);

    public Rat(int health, int speed, Texture texture) {
        this.health = health;
        this.speed = speed;
        this.texture = texture;
        int halfsize = 25;
        this.pos = new Vector2(-10, 430);
        this.cat = new BasicCat();
        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y + halfsize, halfsize * 2, halfsize * 2);
        this.timeAlive = 0;
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
        DOWNS,
        RIGHT,
        LEFT,
        OUT;
    }

    public void addTime() {
        this.timeAlive ++;
        this.secs += 0.05;
        
    }

    public Direction getDirection() {
        if (secs < 4)
        return Direction.RIGHT;

        else if (secs < 9)
        return Direction.UP;

        else if (secs < 14)
        return Direction.RIGHT;

        else if (secs < 24)
        return Direction.DOWNS;

        else if (secs < 31)
        return Direction.LEFT;

        else if (secs < 35)
        return Direction.DOWNS;

        else if (secs < 51)
        return Direction.RIGHT;

        else if (secs < 57)
            return Direction.UP;

        else if (secs < 62)
            return Direction.LEFT;

        else if (secs < 67)
            return Direction.UP;

        else if (secs < 72.5)
            return Direction.RIGHT;

        else if (secs < 78)
            return Direction.UP;

        else if (secs < 85)
            return Direction.LEFT;
        
        else if (secs < 90)
            return Direction.UP;

        return Direction.OUT;
    }

    @Override
    public void move() {
        switch (getDirection()) {
            case UP:
                pos.y += speed;
                break;
            case DOWNS:
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

    public void newRound(){

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
