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

    /**
     * Returns a Rectangle that represents the bounding box ofp the projectile.
     * The rectangle's position is the same as the projectile's position, and its
     * width and height are the same as the projectile's texture's width and height.
     * 
     * @return A Rectangle that represents the bounding box of the projectile.
     */
    public Rectangle getRectangle() {
        int x = (int) position.x;
        int y = (int) position.y;
        int width = texture.getWidth();
        int height = texture.getHeight();
        return new Rectangle(x, y, width, height);
    }

    /**
     * Updates the projectile's position based on its velocity and the given time
     * delta.
     * The velocity is calculated based on the direction from the cat to the rat.
     * 
     * @param dt  The time delta.
     * @param rat The rat.
     * @param cat The cat.
     */
    public void update(float dt, Rat rat, Cat cat) {
        Vector2 direction = new Vector2(rat.getPosition().x - cat.getPosition().x,
                rat.getPosition().y - cat.getPosition().y);
        Vector2 unitDirection = direction.nor();
        velocity = unitDirection.scl(speed * dt);
        position.add(velocity);

    }

    /**
     * Checks if the projectile has hit the given rat.
     * 
     * @param rat The rat to check.
     * @return True if the projectile has hit the rat, false otherwise.
     */
    public boolean ratIsHit(Rat rat) {
        return getRectangle().overlaps(rat.getRectangle());
    }

    /**
     * Points the cat's image at the given rat.
     * The angle is calculated based on the direction from the cat to the rat.
     * 
     * @param rat The rat.
     * @param cat The cat.
     */
    public void pointImageAtRat(Rat rat, Cat cat) {
        float angle = (float) Math.atan2(rat.getPosition().y - cat.getPosition().y,
                rat.getPosition().x - cat.getPosition().x);
        cat.getSprite().setRotation((float) Math.toDegrees(angle));
    }

    /**
     * Returns the projectile's texture.
     * 
     * @return The projectile's texture.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Moves the projectile in its current direction at its current speed.
     */
    public void move() {
        position.add(direction.scl(speed));
    }

    /**
     * Returns the projectile's current position.
     * 
     * @return The projectile's current position.
     */
    public Vector2 getPosition() {
        return position;
    }

}
