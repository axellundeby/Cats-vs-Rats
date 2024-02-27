package inf112.skeleton.app.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Rat implements IEntity {
    private final int speed;
    private Vector2 pos;
    private int health;
    private Path path;
    private float distanceTraveled = 0f;
    private Texture spriteImage;
	private Rectangle spriteRect;
    

    public Rat(int health, int speed, Texture spriteImage){
        this.health = health;
        this.speed = speed;
        this.spriteImage = spriteImage;
        int size = 20;
        // Initialiserer posisjonen til rotta ved å bruke Vector2 direkte
        this.pos = new Vector2(10, -50); // Alle rottene spawner ovenfor brettet

        this.spriteRect = new Rectangle(pos.x, pos.y, size, size);
    }

    public void takeDamage(int damage){
        health -= damage;
        if (isKilled()) {
            killed();
        }
    }
 
    @Override
    public void move(float deltaTime) {
        if (path != null) {
            distanceTraveled += speed * deltaTime;
            Vector2 newPosition = path.getPosition(distanceTraveled);
            if (newPosition != null) {
                pos.set(newPosition);
            }
        }
    }

    public Texture getTexture(){
        return spriteImage;
    }
    public Rectangle getRectangle(){
        return spriteRect;
    }


    @Override
    public void render(SpriteBatch batch) {
        // Anta at du har en Texture for rotta. Du må laste denne et sted i spillet ditt.
        // batch.draw(ratTexture, pos.x, pos.y);
        // Husk å initialisere 'ratTexture' i din faktiske spillkode
    }

    @Override
    public void killed() {
        //removes fra spillet og pop animasjon
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
