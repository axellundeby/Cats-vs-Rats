package inf112.skeleton.app.model;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;

import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.rat.Rat;

public interface ISkadedyrModel {
    /**
     * Adds a Cat object to the list of current cats in the game
     * @param cat the Cat object to be added
     */
    void addCat(Cat cat);
    /**
     * Adds a Rat object to the list of current rats alive in the game
     * @param rat the Rat object to be added
     */
    void addRat(Rat rat);

    /**
     * Gets all cats currently in the game
     * @return an ArrayList of Cat objects
     */
    ArrayList<Cat> getCats();

    /**
     * Gets all currently alive rats in the game
     * @return an ArrayList of Rat objects
     */
    ArrayList<Rat> getRats();

    /**
     * Moves all rats by one
     */
    void moveRats();

    /**
     * Moves all cats by one
     */
    void clockTick();

    /**
     * Sets the game to paused or unpaused
     */
    void setPause();

    /**
     * Checks if the game is paused
     * @return true if the game is paused, false otherwise
     */
    boolean isPaused();

    /**
     * Gets the current speed of the game
     * @return the speed of the game
     */
    float getSpeed();

    /**
     * Sets the speed of the game
     */
    void setSpeed();

    /**
     * Restarts the game by running the main method again
     */
    void restart();

    /**
     * Exits the game
     */
    void exit();

    /**
     * Spawns new rats
     * @return the number of rats spawned
     */
    int getRatsSpawned();

    /**
     * Get the amount of money the player has
     * @return the amount of money the player has
     */
    int getMoney();

    /**
     * Sets the amount of money the player has
     * @param money the amount of money the player has
     */
    void setMoney(int money);

    /**
     * Gets the level of the game
     * @return the level of the game
     */
    int getLevel();


    /**
     * Gets the amount of points the player has
     * @return the amount of points the player has
     */
    int getPoints();


    /**
     * Gets the amount of lives the player has
     * @return the amount of lives the player has
     */
    int getLives();

    /**
     * Gets all projectiles being displayed in the game 
     * @return an ArrayList of Projectile objects
     */
    ArrayList<Projectile> getProjectiles();

    /**
     * Get the cat menu object
     * @return the CatMenu object
     */
    CatMenu getBuyMenu();

    //Javadoc
    Sound getCoinsFromRatSound();

    //javadoc
    Sound getplayerHealtSound();

    void dispose();










}
