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

    protected abstract void setupButton();

    protected abstract void updateButtonAppearance();
}
