package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import java.util.LinkedList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.IRat;

public abstract class Cat implements ICat{
    protected int strength;
    protected int range;
    private Vector2 pos;
    private Circle rangeCircle;
    private int size;
    private int halfSize;
    private EnumMap<PictureSwapper, List<Texture>> textures = new EnumMap<>(PictureSwapper.class);
    private PictureSwapper currentState = PictureSwapper.DEFAULT;
    protected float fireRate;
    private float attackTimer;
    private float attackImageTimer = 0;
    private final float ATTACKIMAGEDURATION = 0.5f;
    private Sprite sprite;
    private float currentRotationAngle;
    private int cost;
    private Vector2 lastTargetPosition = null;
    private Circle selectionCircle;
    private int upgradeCounter = 0;
    private static final float SELECTION_CIRCLE_RADIUS_MULTIPLIER = 5f;
    private static final int MAX_UPGRADES = 4;



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

    @Override
    public abstract void attack(LinkedList<IRat> rats);

    @Override
    public abstract void upgradeDamage();

    @Override
    public abstract void upgradeRange();

    @Override
    public abstract void upgradeFireRate();

    @Override
    public abstract void playAttackSound();

    @Override
    public PictureSwapper getCurrentState() {
        return this.currentState;
    }

    @Override
    public int setUpgradeCounter(int upgradeCounter){
        return this.upgradeCounter = upgradeCounter;
    }

    @Override
    public boolean canUpgrade() {
        return upgradeCounter < MAX_UPGRADES;
    }

    @Override
    public int getUpgradeCount() {
        return upgradeCounter;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void upgradeTexture(){
        upgradeCounter++;
        if (upgradeCounter == 2) {
            updateTexture(PictureSwapper.DEFAULT, 1);
            updateTexture(PictureSwapper.ATTACK, 1);
            this.size += 30;
            this.halfSize = size / 2;
            this.sprite.setSize(size, size);
            this.sprite.setPosition(pos.x - halfSize, pos.y - halfSize);
        }

        if (upgradeCounter == 4) { 
            updateTexture(PictureSwapper.DEFAULT, 2);
            updateTexture(PictureSwapper.ATTACK, 2);
            this.size += 30;
            this.halfSize = size / 2;
            this.sprite.setSize(size, size);
            this.sprite.setPosition(pos.x - halfSize, pos.y - halfSize);
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

    protected void triggerAttackImage() {
        swapImage(PictureSwapper.ATTACK);
        attackImageTimer = ATTACKIMAGEDURATION;
    }

    @Override
    public void updateAnimation(float deltaTime) {
        if (attackImageTimer > 0) {
            attackImageTimer -= deltaTime;
            if (attackImageTimer <= 0) {
                swapImage(PictureSwapper.DEFAULT);
            }
        }
        updateAttackTimer(deltaTime);
    }

    @Override
    public float getAttackImageTimer(){
        return attackImageTimer;
    }

    @Override
    public float getAttackTimer(){
        return attackTimer;
    }
    
    @Override
    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
        this.sprite.setPosition(pos.x - halfSize, pos.y - halfSize);
        circleUpdater();
    }

    @Override
    public Circle getSelectionCircle(){
        return selectionCircle;
    }

    @Override
    public Vector2 getLastTargetPosition() {
        return lastTargetPosition;
    }

    @Override
    public void setLastTargetPosition(Vector2 lastTargetPosition) {
        this.lastTargetPosition = lastTargetPosition;
    }
   
    @Override
    public void setRotationToward(IRat target) {
        if (target != null) {
            float dx = target.getPosition().x - this.pos.x;
            float dy = target.getPosition().y - this.pos.y;
            float angleInRadians = (float) Math.atan2(dy, dx);
            currentRotationAngle = (float) Math.toDegrees(angleInRadians) - 90;
            this.lastTargetPosition = target.getPosition();
            sprite.setRotation(currentRotationAngle); 
        }
    }
    
    @Override
    public float getRotationAngle() {
        return currentRotationAngle;
    }

    @Override
    public void circleUpdater() {
        this.rangeCircle = new Circle(pos, range);
        this.selectionCircle = new Circle(pos, halfSize);
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }


    public enum PictureSwapper {
        DEFAULT,
        ATTACK
    }

    @Override
    public void updateAttackTimer(float deltaTime) {
        if (attackTimer > 0) {
            attackTimer -= deltaTime;
        }
    }

    @Override
    public boolean canAttack() {
        return attackTimer <= 0;
    }

    @Override
    public void resetAttackTimer() {
        attackTimer = fireRate;
    }

    @Override
    public boolean withinRange(IRat target) {
        if (target.getPosition() != null) {
            boolean isWithinRange = rangeCircle.contains(target.getPosition());
            if (!isWithinRange) {
                lastTargetPosition = null;
            }
            return isWithinRange;
            
        }
        return false;
    }
    
    @Override
    public void swapImage(PictureSwapper image) {
        currentState = image;
        List<Texture> stateTextures = textures.get(currentState);
        Texture newTexture = stateTextures.get(Math.min(upgradeCounter, stateTextures.size() - 1));
        sprite.setTexture(newTexture);
        sprite.setOriginCenter();
    }

    @Override
    public Texture getTexture() {
        List<Texture> stateTextures = textures.get(currentState);
        return stateTextures.get(Math.min(upgradeCounter, stateTextures.size() - 1));
    }

    @Override
    public Texture getMenuTexture() {
        List<Texture> defaultTextures = textures.get(PictureSwapper.DEFAULT);
        return defaultTextures.get(0);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Circle getRangeCircle() {
        return rangeCircle;
    }

    @Override
    public int getStrength() {
        return strength;
    }
    
    @Override
    public int getRange() {
        return range;
    }

    @Override
    public float getFireRate() {
        return fireRate;
    }

    @Override
    public Vector2 getPosition() {
        return pos;
    }

    @Override
    public int getCost() {
        return cost;
    }
}

