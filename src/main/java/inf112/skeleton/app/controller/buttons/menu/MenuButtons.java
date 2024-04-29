package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;


public class MenuButtons {
    private SkadedyrModel model;

    private Button playButton;
    private Button exitButton;
    private Button restartButton;
    private Button helpButton;
    private Button pauseButton;
    private Button speedButton;

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 75;
    private static final int MENU_BUTTONS_Y_POS = 750;

    public MenuButtons(SkadedyrModel model) {
        this.model = model;
       
    }

    private Button createButton(String textureUp, String textureDown, int xPosition, Runnable action) {
        Button button = ButtonFactory.createImageButton(textureUp, textureDown);
        button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setPosition(xPosition, MENU_BUTTONS_Y_POS);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                action.run();
                updateButtonAppearance();
            }
        });
        return button;
    }

    public Button playButton() {
        this.playButton = createButton("buttons_game/Spill_Play_Up.png", "buttons_game/Spill_Play_Down.png", 800, () -> model.setStartGame());
        return playButton;
    }

    public Button exitButton() {
        this.exitButton = createButton("buttons_game/Spill_Exit.png", "buttons_game/Spill_Exit.png", 10, () -> model.exit());
        return exitButton;
    }

    public Button restartButton() {
        this.restartButton = createButton("buttons_game/Spill_Restart.png", "buttons_game/Spill_Restart_Down.png", 140, () -> model.restart());
        return restartButton;
    }

    public Button helpButton() {
        this.helpButton = createButton("buttons_game/Spill_Help.png", "buttons_game/Spill_Help.png", 340, () -> model.setHelp());
        return helpButton;
    }

    public Button pauseButton() {
        this.pauseButton = createButton("buttons_game/Spill_Pause_Pause.png", "buttons_game/Spill_Pause_Play.png", 440, () -> model.setPause());
        return pauseButton;
    }

    public Button speedButton() {
        this.speedButton = createButton("buttons_game/Spill_FF.png", "buttons_game/Spill_FF_Down.png", 540, () -> model.setSpeed());
        return speedButton;
    }

    public void updateButtonAppearance() {
        updateButtonAppearance(this.playButton, "buttons_game/Spill_Play_Up.png");
        updateButtonAppearance(this.helpButton, "buttons_game/Spill_Help.png");
        updateButtonAppearance(this.speedButton, "buttons_game/Spill_FF.png");
        updateButtonAppearance(this.pauseButton, "buttons_game/Spill_Pause_Pause.png");
        updateButtonAppearance(this.restartButton, "buttons_game/Spill_Restart.png");
        updateButtonAppearance(this.exitButton, "buttons_game/Spill_Exit.png");
    }

    private void updateButtonAppearance(Button button, String texture) {
        TextureRegionDrawable newDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(texture)));
        button.getStyle().up = newDrawable;
        button.getStyle().down = newDrawable;
    }
}
