package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.states.HelpState;
import inf112.skeleton.app.view.states.MenuState;
import inf112.skeleton.app.view.states.PlayState;

public class MenuButtons {
    private SkadedyrModel model;

    private Button playButton;
    private Button exitButton;
    private Button restartButton;
    private Button helpButton;
    private Button pauseButton;
    private Button speedButton;

    private ButtonFactory buttonFactory;
    private int MENU_BUTTONS_Y_POS = 750;
    private int MENU_BUTTONS_X_POS = 0;

    public MenuButtons(SkadedyrModel model) {
        this.model = model;
        this.buttonFactory = new ButtonFactory(model);

    }

    public Button playButton() {

        if (model.getState() instanceof PlayState || model.getState() instanceof MenuState) {
            MENU_BUTTONS_X_POS = 800;
            MENU_BUTTONS_Y_POS = 400;
        }

        else {
            MENU_BUTTONS_X_POS = 270;
            MENU_BUTTONS_Y_POS = 80;
        }

        this.playButton = buttonFactory.createMenuButton("buttons_game/Spill_Play_Up.png",
                "buttons_game/Spill_Play_Down.png", MENU_BUTTONS_X_POS, MENU_BUTTONS_Y_POS, () -> model.setStartGame());
        return playButton;
    }

    public Button helpButton() {

        if (model.getState() instanceof MenuState || model.getState() instanceof HelpState) {

            MENU_BUTTONS_X_POS = 270;

        } else {
            MENU_BUTTONS_X_POS = 770;
            MENU_BUTTONS_Y_POS = 70;
        }

        this.helpButton = buttonFactory.createMenuButton("buttons_game/Spill_Help.png", "buttons_game/Spill_Help.png",
                MENU_BUTTONS_X_POS, MENU_BUTTONS_Y_POS, () -> model.setHelp());
        return helpButton;
    }

    public Button exitButton() {
        this.exitButton = buttonFactory.createMenuButton("buttons_game/Spill_Exit.png", "buttons_game/Spill_Exit.png",
                10, MENU_BUTTONS_Y_POS, () -> model.exit());
        return exitButton;
    }

    public Button restartButton() {
        this.restartButton = buttonFactory.createMenuButton("buttons_game/Spill_Restart.png",
                "buttons_game/Spill_Restart.png", 140, MENU_BUTTONS_Y_POS, () -> model.restart());
        return restartButton;
    }

    public Button pauseButton() {
        this.pauseButton = buttonFactory.createMenuButton("buttons_game/Spill_Pause_Pause.png",
                "buttons_game/Spill_Pause_Pause.png", 410, MENU_BUTTONS_Y_POS, () -> model.setPause());
        return pauseButton;
    }

    public Button speedButton() {
        this.speedButton = buttonFactory.createMenuButton("buttons_game/Spill_FF.png", "buttons_game/Spill_FF.png", 540,
                MENU_BUTTONS_Y_POS, () -> model.setSpeed());
        return speedButton;
    }

    public void updateButtonAppearance() {
        updateButtonAppearance(pauseButton, "buttons_game/Spill_Pause_Pause.png");
        updateButtonAppearance(speedButton, "buttons_game/Spill_FF.png");
    }

    private void updateButtonAppearance(Button button, String texture) {
        TextureRegionDrawable newDrawable = null;
        if (button != null && button.getStyle() != null) {

            if (model.isPaused() && button.equals(pauseButton)) {
                newDrawable = new TextureRegionDrawable(
                        new TextureRegion(new Texture("buttons_game/Spill_Pause_Pause.png")));
            } else if (button.equals(pauseButton)) {
                newDrawable = new TextureRegionDrawable(
                        new TextureRegion(new Texture("buttons_game/Spill_Pause_Play.png")));
            } else if (model.isSpeedUp() && button.equals(speedButton)) {
                newDrawable = new TextureRegionDrawable(
                        new TextureRegion(new Texture("buttons_game/Spill_FF_Down.png")));
            } else {
                newDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("buttons_game/Spill_FF.png")));
            }

            button.getStyle().up = newDrawable;
            button.getStyle().down = newDrawable;
        }
    }
}
