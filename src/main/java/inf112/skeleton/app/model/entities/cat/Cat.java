package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import java.util.LinkedList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.Rat;

public abstract class Cat {

    protected int strength;
    protected int range;
    private Vector2 pos;
    private Circle rangeCircle;
    private int size;
    private int halfSize;
    private EnumMap<PictureSwapper, List<Texture>> textures = new EnumMap<>(PictureSwapper.class);
    public PictureSwapper currentState = PictureSwapper.DEFAULT;
    protected float fireRate;
    private float attackTimer;
    private float attackImageTimer = 0;
    private final float ATTACKIMAGEDURATION = 0.5f;
    private Sprite sprite;
    private float currentRotationAngle;
    private int cost;
    private Vector2 lastTargetPosition = null;
    private Circle selectionCircle;
    public int upgradeCounter = 0;
    private static final float SELECTION_CIRCLE_RADIUS_MULTIPLIER = 5f;



    public Cat(int strength, int range, List<Texture> defaultImages, List<Texture> attackImages, float fireRate, int cost) {
        this.strength = strength;
        this.range = range;
        this.pos = new Vector2();
        this.size = 100;
        this.fireRate = fireRate;
        this.attackTimer = 0;
        this.halfSize = size / 2;
        this.sprite = new Sprite(defaultImages.get(0));
        this.sprite.setSize(size, size);
        this.sprite.setPosition(pos.x - halfSize, pos.y - halfSize);
        this.circleUpdater(); 
        this.selectionCircle = new Circle(pos.x, pos.y, halfSize * SELECTION_CIRCLE_RADIUS_MULTIPLIER);

        this.cost = cost;
        this.currentRotationAngle = 0;

        textures.put(PictureSwapper.DEFAULT, new ArrayList<>(defaultImages));
        textures.put(PictureSwapper.ATTACK, new ArrayList<>(attackImages));
    }

    public PictureSwapper getCurrentState() {
        return this.currentState;
    }

    public int getUpgradeCounter(){
        return upgradeCounter;
    }

    public int setUpgradeCounter(int upgradeCounter){
        return this.upgradeCounter = upgradeCounter;
    }

    public int getSize(){
        return size;
    }

    
    public void upgradeTexture(){
        upgradeCounter++;
        if (upgradeCounter == 2) {
            updateTexture(PictureSwapper.DEFAULT, 1);
            updateTexture(PictureSwapper.ATTACK, 1);
            this.size += 30;
            this.sprite.setSize(size, size);
        }

        if (upgradeCounter == 4) { 
            updateTexture(PictureSwapper.DEFAULT, 2);
            updateTexture(PictureSwapper.ATTACK, 2);
            this.size += 30;
            this.sprite.setSize(size, size);
        }
    }
    
    private void updateTexture(PictureSwapper state, int index) {
        List<Texture> stateTextures = textures.get(state);
        if (index < stateTextures.size()) { 
            Texture newTexture = stateTextures.get(index);
            if (state == currentState) { 
                sprite.setTexture(newTexture);
                sprite.setOriginCenter();
            }
        }
    }

      /**
     * Triggers the attack image for the cat.
     */
    public void triggerAttackImage() {
        swapImage(PictureSwapper.ATTACK);
        attackImageTimer = ATTACKIMAGEDURATION;
    }

    /**
     * Updates the cat's animation based on the elapsed time.
     *
     * @param deltaTime The time elapsed since the last frame.
     */
    public void updateAnimation(float deltaTime) {
        if (attackImageTimer > 0) {
            attackImageTimer -= deltaTime;
            if (attackImageTimer <= 0) {
                swapImage(PictureSwapper.DEFAULT);
            }
        }
        updateAttackTimer(deltaTime);
    }

    public float getAttackImageTimer(){
        return attackImageTimer;
    }

    public float getAttackTimer(){
        return attackTimer;
    }


    /**
     * Attacks the specified rats.
     *
     * @param rats The rats to attack.
     * @return A list of projectiles fired by the cat.
     */
    public abstract void attack(LinkedList<Rat> rats);

     /**
     * Upgrades the cat's damage.
     */
    public abstract void upgradeDamage();

    /**
     * Upgrades the cat's range.
     */
    public abstract void upgradeRange();

    /**
     * Upgrades the cat's fire rate.
     */
    public abstract void upgradeFireRate();

    /**
     * Sets the cat's position.
     *
     * @param x The new x-coordinate of the cat.
     * @param y The new y-coordinate of the cat.
     */
    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
        this.sprite.setPosition(pos.x - halfSize, pos.y - halfSize);
        circleUpdater();
    }

    /**
     * Gets the cat's Circle for selection in-game
     * @return A Circle-object surrounding the cat sprite
     */
    public Circle getSelectionCircle(){
        return selectionCircle;
    }

    public Vector2 getLastTargetPosition() {
        return lastTargetPosition;
    }

    public void setLastTargetPosition(Vector2 lastTargetPosition) {
        this.lastTargetPosition = lastTargetPosition;
    }
   

    /**
     * Sets the cat's rotation to face the specified rat.
     *
     * @param target The rat that the cat should face.
     */
    public void setRotationToward(Rat target) {
        if (target != null) {
            float dx = target.getPosition().x - this.pos.x;
            float dy = target.getPosition().y - this.pos.y;
            float angleInRadians = (float) Math.atan2(dy, dx);
            currentRotationAngle = (float) Math.toDegrees(angleInRadians) - 90;
            this.lastTargetPosition = target.getPosition();
            sprite.setRotation(currentRotationAngle); 
        }
    }
    

    /**
     * Returns the current rotation angle of the cat.
     *
     * @return The current rotation angle of the cat.
     */
    public float getRotationAngle() {
        return currentRotationAngle;
    }

    /**
     * Updates the cat's range circle.
     * The range circle is used to determine which rats are within the cat's attack range.
     */
    public void circleUpdater() {
        this.rangeCircle = new Circle(pos, range);
        this.selectionCircle = new Circle(pos, halfSize);
    }


    /**
     * Sets the size of the cat.
     *
     * @param size The new size of the cat.
     */
    public void setSize(int size) {
        this.size = size;
    }


    /**
     * Enum representing the possible states of the cat's image.
     */
    public enum PictureSwapper {
        DEFAULT,
        ATTACK
    }

     /**
     * Updates the cat's attack timer based on the elapsed time.
     *
     * @param deltaTime The time elapsed since the last frame.
     */
    public void updateAttackTimer(float deltaTime) {
        if (attackTimer > 0) {
            attackTimer -= deltaTime;
        }
    }

    /**
     * Returns whether the cat can attack.
     *
     * @return True if the cat can attack, false otherwise.
     */
    public boolean canAttack() {
        return attackTimer <= 0;
    }

    /**
     * Resets the cat's attack timer.
     */
    public void resetAttackTimer() {
        attackTimer = fireRate;
    }

    /**
     * Returns whether the specified rat is within the cat's range.
     *
     * @param target The rat to check.
     * @return True if the rat is within the cat's range, false otherwise.
     */
    public boolean withinRange(Rat target) {
        if (target.getPosition() != null) {
            boolean isWithinRange = rangeCircle.contains(target.getPosition());
            if (!isWithinRange) {
                lastTargetPosition = null;
            }
            return isWithinRange;
            
        }
        return false;
    }
    
    

    /**
     * Swaps the cat's image to the specified state.
     *
     * @param image The new state of the cat's image.
     */
   public void swapImage(PictureSwapper image) {
    currentState = image;
    List<Texture> stateTextures = textures.get(currentState);
    Texture newTexture = stateTextures.get(Math.min(upgradeCounter, stateTextures.size() - 1));
    sprite.setTexture(newTexture);
    sprite.setOriginCenter();
}

    /**
     * Returns the current texture of the cat.
     *
     * @return The current texture of the cat.
     */
    public Texture getTexture() {
        List<Texture> stateTextures = textures.get(currentState);
        return stateTextures.get(Math.min(upgradeCounter, stateTextures.size() - 1));
    }

    public Texture getMenuTexture() {
        List<Texture> stateTextures = textures.get(currentState);
        return stateTextures.get(0);
    }

    /**
     * Returns the sprite representing the cat.
     *
     * @return The sprite representing the cat.
     */
    public Sprite getSprite() {
        return sprite;
    }

     /**
     * Returns the circle representing the cat's range.
     *
     * @return The circle representing the cat's range.
     */
    public Circle getRangeCircle() {
        return rangeCircle;
    }

    /**
     * Returns the strength of the cat.
     *
     * @return The strength of the cat.
     */
    public int getStrength() {
        return strength;
    }

    public int getRange() {
        return range;
    }

    public float getFireRate() {
        return fireRate;
    }

    /**
     * Returns the position of the cat.
     *
     * @return The position of the cat.
     */
    public Vector2 getPosition() {
        return pos;
    }

    /**
     * Returns the cost of the cat.
     *
     * @return The cost of the cat.
     */
    public int getCost() {
        return cost;
    }
}

