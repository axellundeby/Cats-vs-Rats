package inf112.skeleton.app.controller.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.model.SkadedyrModel;

public class PauseButton extends Buttons {


    public PauseButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton("pauseUp.png", "playUp.png");
        button.setSize(100, 100);
        button.setPosition(1000, 700);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.setPause();
                updateButtonAppearance();
            }
        });
    }

    @Override
    public void updateButtonAppearance() {
        button.getStyle().up = model.isPaused() 
            ? new TextureRegionDrawable(new TextureRegion(new Texture("playUp.png")))
            : new TextureRegionDrawable(new TextureRegion(new Texture("pauseUp.png")));
    }

        
    }

