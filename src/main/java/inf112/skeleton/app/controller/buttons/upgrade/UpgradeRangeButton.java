package inf112.skeleton.app.controller.buttons.upgrade;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.controller.buttons.Buttons;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;

public class UpgradeRangeButton extends Buttons {
    private static final String normalTexture= "buttons_game/range.png";
    private static final String noMoneyTexture = "buttons_game/noMoney.png";
    private static final String usedUpTexture= "ikkeTilgjengelig.png";
    private static final String clickTexture= "coin.png";

    private int cost = 0;
    private static final int MAX_UPGRADE = 4;
    private static final int MAX_UPGRADE_PER = 3;
    private int upgradeCounter = 0;

    public UpgradeRangeButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton(normalTexture, clickTexture);
        button.setSize(100, 100);
        button.setPosition(1100, 50);
        
        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Cat selectedCat = model.getSelectedCat();
                if (selectedCat != null && selectedCat.getUpgradeCounter() <= MAX_UPGRADE) {
                    if (model.getMoney() >= cost) {
                        selectedCat.upgradeTexture();
                        selectedCat.upgradeRange();
                    } else {
                        model.pressedUppgradeButton(); 
                    }
                }
                updateButtonAppearance(); 
            }
        });
    }



    @Override
    public void updateButtonAppearance() {
        Cat selectedCat = model.getSelectedCat();
        TextureRegionDrawable appearance;

        if (selectedCat != null && selectedCat.getUpgradeCounter() >= MAX_UPGRADE_PER) {
            appearance = new TextureRegionDrawable(new TextureRegion(new Texture(usedUpTexture)));
        } else if (model.getMoney() < cost) {
            appearance = new TextureRegionDrawable(new TextureRegion(new Texture(noMoneyTexture)));
        } else {
            appearance = new TextureRegionDrawable(new TextureRegion(new Texture(normalTexture)));
        }

        button.getStyle().up = appearance;
        button.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture(clickTexture)));
    }
}
