package inf112.skeleton.app.model;

public interface IEntity {
    /**
     * 
     * @return name of entity
     */
    String name();

    /**
     * The object's strength in battle
     * @return Integer value representing the damage to deal to opponent
     */
    int getStrength();
    
    /**
     * Find the object's location on the board
     * @return a coordinate with x, y values
     */
    Coordinate getPosition();

    /**
     * 
     * @return a string representation of the object
     */
    String toString();

}
