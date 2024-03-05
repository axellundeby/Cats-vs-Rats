package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.Path;

public class Rat implements IEntity {
    private final int speed;
    private Vector2 pos;
    private int health;
    private Path path;
    private float distanceTraveled = 0f;
    private Texture spriteImage;
    private Rectangle spriteRect;
    private float secs;
    private int timeAlive;

    public Rat(int health, int speed, Texture spriteImage) {
        this.health = health;
        this.speed = speed;
        this.spriteImage = spriteImage;
        int halfsize = 25;
        // Initialiserer posisjonen til rotta ved å bruke Vector2 direkte
        this.pos = new Vector2(-10, 430); // Alle rottene spawner ovenfor brettet

        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y - halfsize, halfsize * 2, halfsize * 2);
        this.timeAlive = 0;
        this.secs = 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (isKilled()) {
            killed();
        }
    }

    private enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT;
    }

    public void addTime() {
        this.timeAlive ++;
        this.secs += 0.05;
    }

    private Direction getDirection() {
/*         System.out.println(secs); 

        if (timeAlive < 80)
            return Direction.RIGHT;

        else if (timeAlive < 190)
            return Direction.UP;

        else if (timeAlive < 285)
            return Direction.RIGHT;

        else if (timeAlive < 500)
            return Direction.DOWNS;
        
        else if (timeAlive < 700)
            return Direction.LEFT;

        else if (timeAlive < 800)
            return Direction.DOWNS;

        else if (timeAlive < 900)
            return Direction.RIGHT;
 */
    //     if (secs < 4)
    //         return Direction.RIGHT;

    //     else if (secs < 9)
    //         return Direction.UP;

    //     else if (secs < 14)
    //         return Direction.RIGHT;

    //     else if (secs < 24)
    //         return Direction.DOWN;

    //     else if (secs < 31)
    //         return Direction.LEFT;

    //     else if (secs < 35)
    //         return Direction.DOWN;

    //     else if (secs < 51)
    //         return Direction.RIGHT;

    //     else if (secs < 57)
    //         return Direction.UP;

    //     else if (secs < 62)
    //         return Direction.LEFT;

    //     else if (secs < 67)
    //         return Direction.UP;

    //     else if (secs < 72.5)
    //         return Direction.RIGHT;

    //     else if (secs < 78)
    //         return Direction.UP;

    //     else if (secs < 85)
    //         return Direction.LEFT;
        
    //     else if (secs < 87)
    //         return Direction.UP;

    //     throw new Error("Error in Rat movement: Nowhere to go");
    // }
    
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
        // if (path != null) {
        // distanceTraveled += speed * deltaTime;
        // Vector2 newPosition = path.getPosition(distanceTraveled);
        // if (newPosition != null) {
        // pos.set(newPosition);
        // }
        // }

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
            default:
                throw new Error("Error in class Rat");
        }

        spriteRect.x = pos.x;
        spriteRect.y = pos.y;
    }

    public Texture getTexture() {
        return spriteImage;
    }

    public Rectangle getRectangle() {
        return spriteRect;
    }

    @Override
    public void render(SpriteBatch batch) {
        // Anta at du har en Texture for rotta. Du må laste denne et sted i spillet
        // ditt.
        // batch.draw(ratTexture, pos.x, pos.y);
        // Husk å initialisere 'ratTexture' i din faktiske spillkode
    }

    @Override
    public void killed() {
        // removes fra spillet og pop animasjon
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
}
