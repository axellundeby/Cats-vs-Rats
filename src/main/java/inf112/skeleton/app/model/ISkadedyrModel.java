package inf112.skeleton.app.model;

import java.util.ArrayList;

import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.cat.ICat;
import inf112.skeleton.app.model.entities.rat.IRat;
import inf112.skeleton.app.view.states.State;

public interface ISkadedyrModel {
    
    /**
     * Gets all cats currently in the game
     * @return an ArrayList of Cat objects
     */
    ArrayList<ICat> getCats();

    /**
     * Gets all currently alive rats in the game
     * @return an ArrayList of Rat objects
     */
    ArrayList<IRat> getRats();

 
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
    String exit();

  
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
     * Get the cat menu object
     * @return the CatMenu object
     */
    CatMenu getCatMenu();


    /**
     * Checks if the game has been won.
     * @return a boolean value - true if the game has been won, false otherwise.
     */
    boolean isGameWon();


    /**
     * Checks if the game is over.
     * @return a boolean value - true if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Checks if the help menu is open.
     */
    void initCatMenu();


    /**
     * 
     * @param newState the new state of the game
     * sets the state of the game
     */
    void setState(State newState);

    /**
     * 
     * @return the state of the game
     */
    State getState();

    /**
     * @return info about the game based on the game stiuation
     */
    String nextWaveText();

    /**
     * 
     * @return if an upgrade error has occured then true 
     */
    boolean pressedUppgradeButton();

    /**
     * 
     * @return true if an error was detected while placing a cat, false otherwise
     */
    boolean triendToAddCat();


    /**
     * 
     * @return the error message if an error was detected while upgrading a cat
     */
    String uppgradeErrorText();

    /**
     * 
     * @return the error message if an error was detected while placing a cat
     */
    String setErrorText(); 
    
    
    /**
     * @return a list of all alive rats
     */
    ArrayList<IRat> getAliveRats();

    
    /**
     * @param rat the rat to be added
     * adds a rat to the aliveRats list
     */
    void addRat(IRat rat); 

    /**
     * 
     * @return true if the game is speed up, false otherwise
     */
    boolean isSpeedUp();

    /**
     * Sets the level of the game.
     * @param levelSetter the new level to set.
     */
    void setLevel(int levelSetter);

    /**
     * Toggles the help status of the game and sets the start game status to false.
     */
    void setHelp();

    /**
     * Returns the help status of the game.
     * @return the help status of the game.
     */
    boolean getHelp();

    /**
     * Toggles the start game status and sets the help status to false.
     */
    void setStartGame();

    /**
     * Returns the start game status.
     * @return the start game status.
     */
    boolean getStartGame();


    /**
     * @return the selected cat
     */
    ICat getSelectedCat(); 


    /**
     * @param cat the cat to be selected
     * @return the selected cat
     */
    ICat setSelectedCat(ICat cat);

    /**
     * adds a cat to the cats list.
     */
    void addCat(ICat cat);

}
