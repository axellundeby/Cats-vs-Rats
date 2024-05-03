package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.cat.Cat.PictureSwapper;
import inf112.skeleton.app.model.entities.rat.IRat;

public interface ICat {
    /**
     * @param rats a list of rats to attack
     * attacks the rats in the list
     */
    void attack(LinkedList<IRat> rats);
    

    /**
     * Upgrades the damage of the cat
     */
    void upgradeDamage();


    /**
     * Upgrades the range of the cat
     */
   void upgradeRange();

    /**
     * Upgrades the fire rate of the cat
     */
    void upgradeFireRate();

    /**
     * Plays the attack sound of the cat when the cat is attacking
     */
    void playAttackSound();


    /**
     * @return the key of the EnumMap that represents the Texture of the cat
     */
    PictureSwapper getCurrentState();

    /**
     * @param upgradeCounter the number of upgrades 
     * @return sets the number of upgrades
     */
    int setUpgradeCounter(int upgradeCounter);

    /**
     * @return checks if the cat can upgrade
     */
    boolean canUpgrade();


    /**
     * @return the number of upgrades
     */
    int getUpgradeCount();


    /**
     * @return the size of the cat
     */
    int getSize();

    /**
     * upgrades the attackTexture and defaultTexture and size of the cat
     */
    void upgradeTexture();

    /**
     * @param deltaTime the time between frames
     * updates the animation of the cat
     */
    void updateAnimation(float deltaTime);

    /**
     * @return the attackImageTimer
     */
    float getAttackImageTimer();

    /**
     * @return the attackTimer
     */
    float getAttackTimer();

    /**
     * @param x the x position of the cat
     * @param y the y position of the cat
     * sets the position of the cat
     */
    void setPos(float x, float y);


    /**
     * @return selectionCircle
     */
    Circle getSelectionCircle();


    /**
     * @return last targetet rat position
     */
    Vector2 getLastTargetPosition();


    /**
     * @param lastTargetPosition the last target position
     * sets the last target position
     */
    void setLastTargetPosition(Vector2 lastTargetPosition); 

    /**
     * @param target the rat to rotate towards
     * sets the rotation of the cat towards the rat
     */
    void setRotationToward(IRat target);

    /**
     * @return the rotation angle of the cat
     */
    float getRotationAngle();

    /**
     * updates the range and selection sercle of the cat
     */
    void circleUpdater();

    /**
     * @param size the size of the cat
     * sets the size of the cat
     */
    void setSize(int size);

    /**
     * @param deltaTime the time 
     * updates the attack timer of the cat
     */
    void updateAttackTimer(float deltaTime);

    /**
     * 
     * @return true if a can can attack, false otherwise
     */
    boolean canAttack();

    /**
     * sets attacktimer to firerate
     */
    void resetAttackTimer();

    /**
     * @param target the rat to check if is within range
     * @return true if the rat is within range, false otherwise
     */
    boolean withinRange(IRat target);

    /**
     * @param image the image to swap to
     * swaps the image of the cat
     */
    void swapImage(PictureSwapper image);

    /**
     * @return the texture of the cat
     */
    Texture getTexture();

    /**
     * 
     * @return the first default picture of cat
     */
    Texture getMenuTexture();

    /**
     * @return the sprite of the entity.
     */
    Sprite getSprite();

    /**
     * @return the range circle of the entity.
     */
    Circle getRangeCircle();

    /**
     * @return the strength of the entity.
     */
    int getStrength();

    /**
     * @return the range of the entity.
     */
    int getRange();

    /**
     * @return the fire rate of the entity.
     */
    float getFireRate();

    /**
     * @return the position of the entity.
     */
    Vector2 getPosition();

    /**
     * @return the cost of the entity.
     */
    int getCost();
} 
