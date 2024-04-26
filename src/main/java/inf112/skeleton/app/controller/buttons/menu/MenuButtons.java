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
import inf112.skeleton.app.view.States.HelpState;
import inf112.skeleton.app.view.States.MenuState;

public class MenuButtons implements IMenuButtons {
    private SkadedyrModel model;
    private Stage stage;
    private Button pauseButton;
    private Button speedButton;
    private final static int BUTTON_HEIGHT = 50;
    private final static int MENU_BUTTONS_HEIGHT = 750;


    public MenuButtons(SkadedyrModel model, Stage stage) {
        this.model = model;
        this.stage = stage;

    }

    @Override
    public Button exitButton() {
        Button exitButton = ButtonFactory.createImageButton("buttons_game/Spill_Exit.png", "buttons_game/angryCat.png");
        exitButton.setSize(150, 75);
        exitButton.setPosition(5, MENU_BUTTONS_HEIGHT);
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
        //pauseButton.setPosition(540, MENU_BUTTONS_HEIGHT - 10);
        pauseButton.setPosition(440, MENU_BUTTONS_HEIGHT - 10);
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

        Button playButton = ButtonFactory.createImageButton("buttons_game/Spill_Play_Up.png",
                "buttons_game/Spill_Play_Down.png");
        playButton.setSize(200, 100);

        if (model.getState() instanceof PlayState) {
            playButton.setPosition(800, 400);

        } else if (model.getState() instanceof MenuState) {
            playButton.setPosition(800, 400);
        }

        else {
            playButton.setPosition((stage.getWidth() - playButton.getWidth()) / 2 - 200, BUTTON_HEIGHT);
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
            helpButton.setPosition(340, MENU_BUTTONS_HEIGHT - 10);
        } else if (model.getState() instanceof HelpState) {
            helpButton.setPosition(340, MENU_BUTTONS_HEIGHT - 10) ;
        } else {
            helpButton.setPosition((stage.getWidth() - helpButton.getWidth()) / 2 + 200, BUTTON_HEIGHT);
        }

        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (model.isPaused())
                    model.setHelp();

            }
        });
        return helpButton;
    }

    @Override
    public Button restarButton() {
        Button restartButton = ButtonFactory.createImageButton("buttons_game/Spill_Restart.png",
                "buttons_game/angryCat.png");
        restartButton.setSize(150, 75);
        restartButton.setPosition(140, MENU_BUTTONS_HEIGHT);
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
        speedButton.setPosition(540, MENU_BUTTONS_HEIGHT - 10);
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
                    new TextureRegion(new Texture("buttons_game/Spill_Pause_Pause.png")));
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
