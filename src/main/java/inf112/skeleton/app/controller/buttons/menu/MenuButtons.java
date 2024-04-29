package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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

    private ButtonFactory buttonFactory;
    private static final int MENU_BUTTONS_Y_POS = 750;

    public MenuButtons(SkadedyrModel model) {
        this.model = model;
        this.buttonFactory = new ButtonFactory(model);

    }

    public Button playButton() {
        this.playButton = buttonFactory.createMenuButton("buttons_game/Spill_Play_Up.png",
                "buttons_game/Spill_Play_Down.png", 10, MENU_BUTTONS_Y_POS, () -> model.setStartGame());
        return playButton;
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

    public Button helpButton() {
        this.helpButton = buttonFactory.createMenuButton("buttons_game/Spill_Help.png", "buttons_game/Spill_Help.png",
                270, MENU_BUTTONS_Y_POS, () -> model.setHelp());
        return helpButton;
    }

    public Button pauseButton() {
        this.pauseButton = buttonFactory.createMenuButton("buttons_game/Spill_Pause_Pause.png",
                "buttons_game/Spill_Pause_Pause.png", 400, MENU_BUTTONS_Y_POS, () -> model.setPause());
        return pauseButton;
    }

    public Button speedButton() {
        this.speedButton = buttonFactory.createMenuButton("buttons_game/Spill_FF.png", "buttons_game/Spill_FF.png", 530,
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
            }
            else if (model.isSpeedUp() && button.equals(speedButton)) {
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

// public void updateButtonAppearance() {

// if (model.isPaused()) {
// pauseButton.getStyle().up = new TextureRegionDrawable(
// new TextureRegion(new Texture("buttons_game/Spill_Pause_Pause.png")));
// } else {
// pauseButton.getStyle().up = new TextureRegionDrawable(
// new TextureRegion(new Texture("buttons_game/Spill_Pause_Play.png")));
// }
// if (model.isSpeedUp()) {
// speedButton.getStyle().up = new TextureRegionDrawable(
// new TextureRegion(new Texture("buttons_game/Spill_FF_Down.png")));
// } else {

// speedButton.getStyle().up = new TextureRegionDrawable(
// new TextureRegion(new Texture("buttons_game/Spill_FF.png")));
// }

// }