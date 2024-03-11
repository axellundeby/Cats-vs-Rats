package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.Rat;

public abstract class Cat {

    private int strength;
    private float range;
    private Vector2 pos;
    private Texture spriteImage;
    private Rectangle spriteRect;
    private Circle rangeCircle;
    private int size;
    private int halfSize;

    public Cat(int strength, float range, Texture spriteImage) {
        this.strength = strength;
        this.range = range;
        this.spriteImage = spriteImage;
        this.pos = new Vector2();
        this.size = 60;

        this.halfSize = size / 2;

        this.spriteRect = new Rectangle(pos.x - halfSize, pos.y - halfSize, size, size);
        this.rangeCircle = new Circle(pos, range);
    }
    public abstract void attack(LinkedList<Rat> rats);

    public void setPos(int x, int y) {
        pos.x = x;
        pos.y = y;
        this.spriteRect = new Rectangle(pos.x - halfSize, pos.y - halfSize, size, size);
        this.rangeCircle = new Circle(pos, range);

    }

    
    public boolean withinRange(Rat target) {
        Vector2 ratPos = target.getPosition();
        return rangeCircle.contains(ratPos);
    }

    public Texture getTexture() {
        return spriteImage;
    }

    public Rectangle getRectangle() {
        return spriteRect;
    }

    public Circle getRangeCircle() {
        return rangeCircle;
    }

    public int getStrength() {
        return strength;
    }

    public Vector2 getPosition() {
        return pos;
    }

    @Override
    public String toString() {
        return "Cat at position: " + pos + " with strength " + strength + " and range " + range;

    }
}
