package inf112.skeleton.app.controller.buttons;

import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;

public class ButtonFactory {

    private final SkadedyrModel model;
    private final int MENU_BUTTON_WIDTH = 140;
    private final int MENU_BUTTON_HEIGHT = 70;
    private final int UPGRADE_BUTTON_WIDTH = 160;
    private final int UPGRADE_BUTTON_HEIGHT = 80;

   
    public ButtonFactory(SkadedyrModel model) {
        this.model = model;
       
    }

    /**
     * Creates a new ImageButton with the specified images for the up and down
     * states.
     *
     * @param upImagePath   The path to the image to use for the button's up state.
     * @param downImagePath The path to the image to use for the button's down
     *                      state.
     * @return A new ImageButton with the specified images.
     */
    public static ImageButton createImageButton(String upImagePath, String downImagePath) {

        Drawable upDrawable = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(upImagePath))));
        Drawable downDrawable = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(downImagePath))));
        ImageButtonStyle style = new ImageButtonStyle();
        style.up = upDrawable;
        style.down = downDrawable;

        return new ImageButton(style);
    }

    public Button createUpgradeButton(int cost, String textureUp, String textureDown, int xPosition, int yPosition,
            Consumer<Cat> upgradeAction) {
        Button upgradeButton = ButtonFactory.createImageButton(textureUp, textureDown);
        upgradeButton.setSize(UPGRADE_BUTTON_WIDTH, UPGRADE_BUTTON_HEIGHT);
        upgradeButton.setPosition(xPosition, yPosition);
        upgradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Cat selectedCat = model.getSelectedCat();
                if (selectedCat != null && model.getMoney() >= cost && selectedCat.getUpgradeCount() < 4) {
                    upgradeAction.accept(selectedCat);
                    model.setMoney(model.getMoney() - cost);
                    selectedCat.upgradeTexture();
                    //updateButtonAppearance();
                }
            }
        });

        return upgradeButton;
    }

    public Button createMenuButton(String textureUp, String textureDown, int xPosition, int yPosition,  Runnable action) {
        Button button = ButtonFactory.createImageButton(textureUp, textureDown);
        button.setSize(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        button.setPosition(xPosition, yPosition);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                action.run();
                //updateButtonAppearance();
            }
        });
        return button;
    }

}