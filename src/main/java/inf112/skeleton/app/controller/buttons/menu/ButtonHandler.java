package inf112.skeleton.app.controller.buttons.menu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import inf112.skeleton.app.model.SkadedyrModel;

public class ButtonHandler {
    protected  SkadedyrModel model;
    protected Stage stage;

    public ButtonHandler(SkadedyrModel model, Stage stage, int width, int height, int x, int y, Texture unpressed, Texture pressed) {
        this.model = model;
        this.stage = stage;
    }

    public void buttonPressed() {
        
    }
}
