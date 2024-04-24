package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.controller.buttons.Buttons;
import inf112.skeleton.app.model.SkadedyrModel;

public class SpeedButton extends Buttons {

    public SpeedButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton("buttons_game/Spill_FF.png", "buttons_game/Spill_FF_Down.png");
        button.setSize(100, 100);
        button.setPosition(500, 50);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.setSpeed();
                updateButtonAppearance();
            }
        });

    }

    @Override
    public void updateButtonAppearance() {
        // if (model.isSpeedUp()) {
        //     button.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("buttons_game/Spill_FF_Down.png")));
        // } else {

        //     button.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("buttons_game/Spill_FF.png")));
        // }
    }
}
