package inf112.skeleton.app.model.catmenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.cat.ICat;
/**
 * Interface for a menu that allows the player to select a cat.
 */
public interface ICatMenu {

    /**
     * Initializes the cat menu.
     */
    void init();

    /**
     * Draws the cat menu using the provided SpriteBatch.
     *
     * @param batch The SpriteBatch object used for drawing.
     * @param playerMoney The current amount of money the player has.
     */
    void draw(SpriteBatch batch, int playerMoney);

    /**
     * Draws the cat menu using the provided ShapeRenderer.
     *
     * @param shapeRenderer The ShapeRenderer object used for drawing.
     */
    void draw(ShapeRenderer shapeRenderer);

    /**
     * Selects a cat based on the provided position.
     *
     * @param pos The position used to select a cat.
     */
    void selector(Vector2 pos);


    /**
     * Deselects the currently selected cat.
     */
    void deselect();
    /**
     * Returns the currently selected cat.
     *
     * @return The currently selected cat.
     */
    ICat getSelectedCat();

    /**
     * Returns the x-coordinate of the cat menu.
     *
     * @return The x-coordinate of the cat menu.
     */
    float getX();

    /**
     * Returns the y-coordinate of the cat menu.
     *
     * @return The y-coordinate of the cat menu.
     */
    float getY();
}
