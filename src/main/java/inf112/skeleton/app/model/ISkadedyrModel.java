package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.LinkedList;

import inf112.skeleton.app.model.entities.Cat;
import inf112.skeleton.app.model.entities.Rat;

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
}
