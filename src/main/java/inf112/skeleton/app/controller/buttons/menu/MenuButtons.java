package inf112.skeleton.app.controller.buttons.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.LibGDXResourceFactory;
import inf112.skeleton.app.view.States.HelpState;
import inf112.skeleton.app.view.States.MenuState;
import inf112.skeleton.app.view.States.PlayState;

public class MenuButtons implements IMenuButtons {
    private SkadedyrModel model;

    private Button playButton;
    private Button exitButton;
    private Button restartButton;
    private Button helpButton;
    private Button pauseButton;
    private Button speedButton;

    private ButtonFactory buttonFactory;
    private LibGDXResourceFactory resourceFactory;

    private int MENU_BUTTONS_Y_POS = 750;
    private int MENU_BUTTONS_X_POS = 0;

    public MenuButtons(SkadedyrModel model) {
        this.model = model;
        this.buttonFactory = new ButtonFactory(model);
        this.resourceFactory = new LibGDXResourceFactory();

    }


    @Override
    public Button playButton() {

        if (model.getState() instanceof PlayState || model.getState() instanceof MenuState) {
            MENU_BUTTONS_X_POS = 800;
            MENU_BUTTONS_Y_POS = 400;
        }

        else {
            MENU_BUTTONS_X_POS = 270;
            MENU_BUTTONS_Y_POS = 80;
        }

        this.playButton = buttonFactory.createMenuButton(resourceFactory.getTexture("buttons_game/Spill_Play_Down.png"),
               resourceFactory.getTexture("buttons_game/Spill_Play_Up.png"), MENU_BUTTONS_X_POS, MENU_BUTTONS_Y_POS, () -> model.setStartGame());
        return playButton;
    }

    @Override
    public Button helpButton() {

        if (model.getState() instanceof MenuState || model.getState() instanceof HelpState) {

            MENU_BUTTONS_X_POS = 270;

        } else {
            MENU_BUTTONS_X_POS = 770;
            MENU_BUTTONS_Y_POS = 70;
        }

        this.helpButton = buttonFactory.createMenuButton(resourceFactory.getTexture("buttons_game/Spill_Help.png"), resourceFactory.getTexture("buttons_game/Spill_Help.png"),
                MENU_BUTTONS_X_POS, MENU_BUTTONS_Y_POS, () -> model.setHelp());
        return helpButton;
    }

    @Override
    public Button exitButton() {
        this.exitButton = buttonFactory.createMenuButton(resourceFactory.getTexture("buttons_game/Spill_Exit.png"), resourceFactory.getTexture("buttons_game/Spill_Exit.png"),
                10, MENU_BUTTONS_Y_POS, () -> model.exit());
        return exitButton;
    }

    @Override
    public Button restartButton() {
        this.restartButton = buttonFactory.createMenuButton(resourceFactory.getTexture("buttons_game/Spill_Restart.png"),
                resourceFactory.getTexture("buttons_game/Spill_Restart.png"), 140, MENU_BUTTONS_Y_POS, () -> model.restart());
        return restartButton;
    }

    @Override
    public Button pauseButton() {
        this.pauseButton = buttonFactory.createMenuButton(resourceFactory.getTexture("buttons_game/Spill_Pause_Play.png"),
                resourceFactory.getTexture("buttons_game/Spill_Pause_Pause.png"), 410, MENU_BUTTONS_Y_POS, () -> model.setPause());
        return pauseButton;
    }

    @Override
    public Button speedButton() {
        this.speedButton = buttonFactory.createMenuButton(resourceFactory.getTexture("buttons_game/Spill_FF.png"), resourceFactory.getTexture("buttons_game/Spill_FF.png"), 540,
                MENU_BUTTONS_Y_POS, () -> model.setSpeed());
        return speedButton;
    }

    @Override
    public void updateButtonAppearance() {
        updateButtonAppearance(pauseButton, resourceFactory.getTexture("buttons_game/Spill_Pause_Play.png"));
        updateButtonAppearance(speedButton, resourceFactory.getTexture("buttons_game/Spill_FF.png"));
    }

    private void updateButtonAppearance(Button button, Texture texture) {
        TextureRegionDrawable newDrawable = null;
        if (button != null && button.getStyle() != null) {

            if (model.isPaused() && button.equals(pauseButton)) {
                newDrawable = new TextureRegionDrawable(
                        new TextureRegion(resourceFactory.getTexture("buttons_game/Spill_Pause_Pause.png")));
            } else if (button.equals(pauseButton)) {
                newDrawable = new TextureRegionDrawable(
                        new TextureRegion(resourceFactory.getTexture("buttons_game/Spill_Pause_Play.png")));
            } else if (model.isSpeedUp() && button.equals(speedButton)) {
                newDrawable = new TextureRegionDrawable(
                        new TextureRegion(resourceFactory.getTexture("buttons_game/Spill_FF.png")));
            } else {
                newDrawable = new TextureRegionDrawable(new TextureRegion(texture));
            }

            button.getStyle().up = newDrawable;
            button.getStyle().down = newDrawable;
        }
    }
}
