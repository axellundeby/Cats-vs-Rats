package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.States.PlayState;
import inf112.skeleton.app.view.States.MenuState;



public class MenuButtons implements IMenuButtons {
    private SkadedyrModel model;
    private Stage stage;
    private Button pauseButton;
    private Button speedButton;
    private final static int BUTTON_HEIGHT=50; 
    

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
        pauseButton = ButtonFactory.createImageButton("buttons_game/Spill_Pause_Pause.png",
                "buttons_game/Spill_Pause_Play.png");
        pauseButton.setSize(100, 100);
        pauseButton.setPosition(600, 70);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.setPause();
                updateButtonAppearance();
            }
        });
        return pauseButton;
    }

    @Override
    public Button playButton() {
        Button playButton = ButtonFactory.createImageButton("buttons_game/Spill_Play_Up.png", "buttons_game/Spill_Play_Down.png");
        playButton.setSize(200, 100);
        if (model.getState() instanceof MenuState) {
            playButton.setPosition((stage.getWidth() - playButton.getWidth()) / 2, stage.getHeight() - 500); 
        } else {
            playButton.setPosition(500, 500);
        }
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                model.setStartGame();
            }
        });
        return playButton;
    }

    @Override
    public Button helpButtonPlay() {
        Button helpButton = ButtonFactory.createImageButton("buttons_game/Spill_Help.png",
                "buttons_game/Spill_Help.png");
        helpButton.setSize(100, 100);
        
        if (model.getState() instanceof MenuState) {
            helpButton.setPosition((stage.getWidth() - helpButton.getWidth()) / 2, stage.getHeight() - 200); 
        } else {
            helpButton.setPosition(270, 750);
        }

        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if(model.isPaused())
                    model.setHelp();
              
            }
        });
        return helpButton;
    }


    @Override
    public Button restarButton() {
        Button restartButton = ButtonFactory.createImageButton("buttons_game/Spill_Restart.png",
                "buttons_game/angryCat.png");
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
        speedButton.setPosition(500, 70);
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
    public void updateButtonAppearance() {

        if (model.isPaused()) {
            pauseButton.getStyle().up = new TextureRegionDrawable(
                    new TextureRegion(new Texture("buttons_game/Spill_Pause_Play.png")));
        } else {
            pauseButton.getStyle().up = new TextureRegionDrawable(
                    new TextureRegion(new Texture("buttons_game/Spill_Pause_Play.png")));
        }
        if (model.isSpeedUp()) {
            speedButton.getStyle().up = new TextureRegionDrawable(
                    new TextureRegion(new Texture("buttons_game/Spill_FF_Down.png")));
        } else {

            speedButton.getStyle().up = new TextureRegionDrawable(
                    new TextureRegion(new Texture("buttons_game/Spill_FF.png")));
        }

    }

}
