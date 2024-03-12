package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class Projectile {
    private Vector2 position;
    private Vector2 direction;
    private Vector2 velocity; 
    private float speed;
    private Texture texture;

    public Projectile(Vector2 position, Vector2 direction, float speed, Texture texture) {
        this.position = position;
        this.direction = direction.nor(); 
        this.speed = speed;
        this.texture = texture;
    }

    public Rectangle getRectangle() {
        int x = (int) position.x;
        int y = (int) position.y;
        int width = texture.getWidth();
        int height = texture.getHeight();
        return new Rectangle(x, y, width, height);
    }

    public void update(float dt, Rat rat, Cat cat) {
        Vector2 direction = new Vector2(rat.getPosition().x - cat.getPosition().x, rat.getPosition().y - cat.getPosition().y);
        Vector2 unitDirection = direction.nor();
        velocity = unitDirection.scl(speed * dt);
        position.add(velocity);
        
    }

    public boolean ratIsHit(Rat rat) {
        return getRectangle().overlaps(rat.getRectangle());
    }

    public void pointImageAtRat(Rat rat, Cat cat) {
        float angle = (float) Math.atan2(rat.getPosition().y - cat.getPosition().y, rat.getPosition().x - cat.getPosition().x);
        cat.getSprite().setRotation((float) Math.toDegrees(angle));
    }


    public Texture getTexture() {
        return texture;
    }

    public void move() {
        position.add(direction.scl(speed));
    }

  
    public Vector2 getPosition() {
        return position;
    }


}
