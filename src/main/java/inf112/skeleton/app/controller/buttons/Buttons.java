package inf112.skeleton.app.controller.buttons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import inf112.skeleton.app.model.SkadedyrModel;

public abstract class Buttons {
    protected static SkadedyrModel model;
    protected Stage stage;
    protected ImageButton button;

    public Buttons(SkadedyrModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
        setupButton();
        stage.addActor(button);
    }

    /**
     * Sets up the button. This method should be implemented to initialize the button's properties,
     * such as its size, position, and click listener.
     */
    protected abstract void setupButton();

    /**
     * Updates the appearance of the button based on the current game state.
     * This method should be implemented to change the button's image or style
     * in response to changes in the game state.
     */
    protected abstract void updateButtonAppearance();
}
