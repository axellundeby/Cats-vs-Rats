package inf112.skeleton.app.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Rat implements Ientity {
    private final int speed;
    private Vector2 pos;
    private int health;
    private Path path;
    private float distanceTraveled = 0f;
    

    public Rat(int health, int speed){
        this.health = health;
        this.speed = speed;
        // Initialiserer posisjonen til rotta ved å bruke Vector2 direkte
        this.pos = new Vector2(10, -50); // Alle rottene spawner ovenfor brettet
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
