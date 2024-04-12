package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.controller.buttons.Buttons;

import inf112.skeleton.app.model.SkadedyrModel;

public class ExitButton  extends Buttons {
    public ExitButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton("buttons_game/Spill_Exit.png", "cat2.png");
        button.setSize(100, 100);
        button.setPosition(850, 600);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
              model.exit();
            }
        });

    }

    @Override
    public void updateButtonAppearance() {

    }
    
}
