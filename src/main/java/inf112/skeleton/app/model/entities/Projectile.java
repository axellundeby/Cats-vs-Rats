package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Vector2 direction;
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
