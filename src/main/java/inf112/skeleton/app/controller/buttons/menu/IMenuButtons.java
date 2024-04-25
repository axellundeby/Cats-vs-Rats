package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * Interface for menu buttons.
 */
public interface IMenuButtons {

    /**
     * Creates and returns an exit button.
     * @return the created exit button.
     */
    Button exitButton();

    /**
     * Creates and returns a pause button.
     * @return the created pause button.
     */
    Button pauseButton();

    /**
     * Creates and returns a speed button.
     * @return the created speed button.
     */
    Button speedButton();

    /**
     * Creates and returns a restart button.
     * @return the created restart button.
     */
    Button restarButton();

    /**
     * Updates the appearance of the buttons.
     */
    void updateButtonAppearance();
}
