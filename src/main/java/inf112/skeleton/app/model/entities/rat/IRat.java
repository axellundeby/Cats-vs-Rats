package inf112.skeleton.app.model.entities.rat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;

public interface IRat {

    /**
     * Tegner rotten på skjermen ved bruk av en SpriteBatch.
     * 
     * @param batch SpriteBatch som brukes for å tegne rotten, sikrer effektiv
     *              tegning.
     */
    void render(com.badlogic.gdx.graphics.g2d.SpriteBatch batch);

    /** Metode for å håndtere hva som skjer når en rotte blir drept */
    void killedAnimation();

    /**
     * Sjekker om rotten er fanget eller ikke.
     * 
     * @return true hvis rotten er fanget, ellers false.
     */
    boolean isKilled();

    /**
     * Returnerer rottens gjenværende helsepoeng.
     * 
     * @return Rottens gjenværende helsepoeng.
     */
    int getHealth();

    /**
     * Returnerer rottens posisjon i spillet.
     * 
     * @return En Vector2 som representerer rottens posisjon.
     */
    Vector2 getPosition();

    /**
     * Checks if the reward has been claimed.
     * 
     * @return true if the reward has been claimed, false otherwise.
     */
    boolean isrewardClaimed();

    /**
     * Checks if the rat has exited the game.
     * 
     * @return true if the rat has exited the game, false otherwise.
     */
    boolean isExited();

    /**
     * Marks the reward as claimed.
     */
    void rewardClaimed();

    /**
     * Marks the rat as exited.
     */
    void exit();

    /**
     * Checks if the rat is hit by any of the given projectiles.
     * 
     * @param projectiles The list of projectiles to check.
     * @return true if the rat is hit by any projectile, false otherwise.
     */
    boolean isHitByProjectile(ArrayList<Projectile> projectiles);

    /**
     * Returns the projectile that hits the rat.
     * 
     * @param projectiles The list of projectiles to check.
     * @return The projectile that hits the rat, or null if no projectile hits the
     *         rat.
     */
    Projectile getHitByProjectile(ArrayList<Projectile> projectiles);

    /**
     * Returns the bounty of the rat.
     * 
     * @return The bounty of the rat.
     */
    int getBounty();

    /**
     * Returns the points of the rat.
     * 
     * @return The points of the rat.
     */
    int getPoints();

    /**
     * Returns the current texture of the rat.
     * 
     * @return The current texture of the rat.
     */
    Texture getTexture();

    /**
     * Returns the rectangle representing the rat.
     * 
     * @return The rectangle representing the rat.
     */
    Rectangle getRectangle();

    // må kanskje endre denne, hvis et prosjektil treffer en rotte, så skal den ta
    // skade. Er det berde.
    /**
     * Reduces the health of the rat by the given damage.
     * 
     * @param damage The amount of damage to inflict on the rat.
     */
    void takeDamage(int damage);

    /**
     * Enum representing the possible directions of the rat.
     */
    // enum Direction;

    /**
     * Adds time to the rat's internal timer.
     */
    void addTime();

    /**
     * Rotates the rat's image to face its direction.
     */
    void rotateImage();

   

    /**
     * Checks if the rat is out.
     * 
     * @return true if the rat is out, false otherwise.
     */
    boolean isOut();

    /**
     * Sets the position of the rat.
     * 
     * @param pos The new position of the rat.
     */
    void setPosition(Vector2 pos);

    /**
     * Freezes the rat.
     */
    void freeze(float deltaTime);
}
