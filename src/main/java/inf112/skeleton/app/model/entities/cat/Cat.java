package inf112.skeleton.app.model.entities.cat;

import java.util.EnumMap;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.ImageSwapper;

public abstract class Cat {

    private int strength;
    private float range;
    private Vector2 pos;
    private Texture defualtImage;
    private Texture attackImage;
    private Rectangle spriteRect;
    private Circle rangeCircle;
    private int size;
    private int halfSize;
    private EnumMap<PictureSwapper, Texture> textures = new EnumMap<>(PictureSwapper.class);
    public PictureSwapper currentState = PictureSwapper.ATTACK;

    public Cat(int strength, float range, Texture defualtImage, Texture attackImage) {
        this.strength = strength;
        this.range = range;
        this.defualtImage = defualtImage;
        this.pos = new Vector2();
        this.size = 60;

        this.halfSize = size / 2;

        this.spriteRect = new Rectangle(pos.x - halfSize, pos.y - halfSize, size, size);
        this.rangeCircle = new Circle(pos, range);

        textures.put(PictureSwapper.DEFAULT, defualtImage);
        textures.put(PictureSwapper.ATTACK, attackImage);
    }
    public abstract void attack(LinkedList<Rat> rats);

    public void setPos(int x, int y) {
        pos.x = x;
        pos.y = y;
        this.spriteRect = new Rectangle(pos.x - halfSize, pos.y - halfSize, size, size);
        this.rangeCircle = new Circle(pos, range);
    }

    public enum PictureSwapper{
        DEFAULT,
        ATTACK
    }

    public boolean withinRange(Rat target) {
        Vector2 ratPos = target.getPosition();
        return rangeCircle.contains(ratPos);
    }

    public void swapImage(PictureSwapper image) {
        currentState = image; 
    }

    public Texture getTexture() {
        return textures.get(currentState); 
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
