package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;

public class MenuButtons implements IMenuButtons{
    private SkadedyrModel model;
    private Stage stage;
    private Button pauseButton;
    private Button speedButton;

    public MenuButtons(SkadedyrModel model, Stage stage) {
        this.model = model;
        this.stage = stage;

        
    }

    @Override
    public Button exitButton() {
        Button exitButton = ButtonFactory.createImageButton("buttons_game/Spill_Exit.png", "buttons_game/angryCat.png");
        exitButton.setSize(100, 100);
        exitButton.setPosition(10, 740);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.exit();
               
            }
        });
        return exitButton;
    }
@Override
    public Button pauseButton() {
        pauseButton = ButtonFactory.createImageButton("buttons_game/Spill_Pause_Pause.png", "buttons_game/Spill_Pause_Play.png");
        pauseButton.setSize(100, 100);
        pauseButton.setPosition(600, 50);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.setPause();
                updateButtonAppearance();
            }
        });
        return pauseButton;
    }

    // public Button helpButton() {
    //     Button button = ButtonFactory.createImageButton("buttons_game/Spill_Help.png", "buttons_game/Spill_Play-Down.png");
    //     button.setSize(200, 150);
    //     button.setPosition((stage.getWidth() - button.getWidth()) / 2 + 200, 50);
    //     button.addListener(new ClickListener() {
    //         @Override
    //         public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
    //             model.help();
    //             updateButtonAppearance();   
    //         }
    //     });
    //     return button;
    // }

    @Override
    public Button restarButton() {
        Button restartButton = ButtonFactory.createImageButton("buttons_game/Spill_Restart.png", "buttons_game/angryCat.png");
        restartButton.setSize(85, 85);
        restartButton.setPosition(140, 750);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.restart();
                
            }
        });
        return restartButton;
    }

    @Override
    public Button speedButton() {
        speedButton = ButtonFactory.createImageButton("buttons_game/Spill_FF.png", "buttons_game/Spill_FF_Down.png");
        speedButton.setSize(100, 100);
        speedButton.setPosition(500, 50);
        speedButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.setSpeed();
                updateButtonAppearance();
            }
        });
        return speedButton;
    }

    @Override
    public void updateButtonAppearance(){


        if(model.isPaused()){
            pauseButton.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("buttons_game/Spill_Pause_Play.png")));
        } else {
            pauseButton.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("buttons_game/Spill_Pause_Play.png")));
        }
        if (model.isSpeedUp()) {
            speedButton.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("buttons_game/Spill_FF_Down.png")));
        } else {

            speedButton.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("buttons_game/Spill_FF.png")));
        }


    }


   
}
