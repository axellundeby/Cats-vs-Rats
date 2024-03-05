package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.Path;

public class Rat implements IEntity {
    private int speed;
    private Vector2 pos;
    private int health;
    private Path path;
    private float distanceTraveled = 0f;
    private Texture spriteImage;
    private Rectangle spriteRect;
    private float secs;
    private int timeAlive;
    private Cat cat;
    

    public Rat(int health, int speed, Texture spriteImage) {
        this.health = health;
        this.speed = speed;
        this.spriteImage = spriteImage;
        int halfsize = 25;
        // Initialiserer posisjonen til rotta ved å bruke Vector2 direkte
        this.pos = new Vector2(-10, 430); // Alle rottene spawner ovenfor brettet
        this.cat = new BasicCat();
        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y + halfsize, halfsize * 2, halfsize * 2);
        this.timeAlive = 0;
        this.secs = 0;
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

    public void attackable(){
        if (this.cat.withinRange(this)){
            //this.cat.shoot(this);
            this.killed();
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
        pos.x = -100;
        pos.y = -100;
        speed = 0;
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
