package inf112.skeleton.app.model.entities.cat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat;

public abstract class Cat {

    private int strength;
    private float range;
    private Vector2 pos;
    private Circle rangeCircle;
    private int size;
    private int halfSize;
    private EnumMap<PictureSwapper, Texture> textures = new EnumMap<>(PictureSwapper.class);
    public PictureSwapper currentState = PictureSwapper.DEFAULT;
    private float fireRate; 
    private float attackTimer;
    private float attackImageTimer = 0; 
    private final float attackImageDuration = 0.5f; 
    private Sprite sprite;
    private float currentRotationAngle = 0; 

     public Cat(int strength, float range, Texture defaultImage, Texture attackImage, float fireRate) {
        this.strength = strength;
        this.range = range;
        this.pos = new Vector2();
        this.size = 60;
        this.fireRate = fireRate; 
        this.attackTimer = 0;
        this.halfSize = size / 2;
        this.sprite = new Sprite(defaultImage);
        this.sprite.setSize(size, size);
        this.sprite.setPosition(pos.x - halfSize, pos.y - halfSize);
        this.rangeCircle = new Circle(pos, range);
    
        textures.put(PictureSwapper.DEFAULT, defaultImage);
        textures.put(PictureSwapper.ATTACK, attackImage);
    }
    public abstract ArrayList<Projectile> attack(LinkedList<Rat> rats);

    public void setPos(int x, int y) {
        pos.x = x;
        pos.y = y;
        this.sprite.setPosition(pos.x - halfSize, pos.y - halfSize);
        this.rangeCircle = new Circle(pos, range);
    }

    public abstract Projectile shootAt(LinkedList<Rat> targets);
      

    //rotate er bugga
    public void rotateImage(Rat target) {
        float dx = target.getPosition().x - this.pos.x;
        float dy = target.getPosition().y - this.pos.y;
        float angleInRadians = (float) Math.atan2(dy, dx);
        currentRotationAngle = (float) Math.toDegrees(angleInRadians) - 90;
        this.sprite.setOriginCenter();
        this.sprite.setRotation(currentRotationAngle);
    }

    public float getRotationAngle() {
        return currentRotationAngle;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void triggerAttackImage() {
        swapImage(PictureSwapper.ATTACK);
        attackImageTimer = attackImageDuration;
    }

    public void updateAnimation(float deltaTime) {
        if (attackImageTimer > 0) {
            attackImageTimer -= deltaTime;
            if (attackImageTimer <= 0) {
                swapImage(PictureSwapper.DEFAULT); 
            }
        }
        updateAttackTimer(deltaTime);
    }

    public enum PictureSwapper{
        DEFAULT,
        ATTACK
    }

    public void updateAttackTimer(float deltaTime) {
        if (attackTimer > 0) {
            attackTimer -= deltaTime;
        }
    }
    
    public boolean canAttack() {
        return attackTimer <= 0;
    }
    
    public void resetAttackTimer() {
        attackTimer = fireRate;
    }
    
    public boolean withinRange(Rat target) {
        Rectangle ratRect = target.getRectangle();
        return Intersector.overlaps(rangeCircle, ratRect);
    }

    public void swapImage(PictureSwapper image) {
        currentState = image;
        Texture newTexture = textures.get(currentState);
        sprite.setTexture(newTexture);
        sprite.setOriginCenter();
        sprite.setRotation(getRotationAngle());
    }
    
    

    public Texture getTexture() {
        return textures.get(currentState); 
    }

    public Sprite getSprite() {
        return sprite;
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
