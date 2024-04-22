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

public class UpgradeFireRateButton extends Buttons {
    private static final String normalTexture= "buttons_game/firerate.png";
    private static final String noMoneyTexture = "buttons_game/noMoney.png";
    private static final String usedUpTexture= "buttons_game/angryCat.png";
    private static final String clickTexture= "coin.png";

    private int cost = 0;
    private boolean maxUpgrade = false;

    public UpgradeFireRateButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton(normalTexture, clickTexture);
        button.setSize(100, 100);
        button.setPosition(975, 50);
        
        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (model.getMoney() >= cost) {
                    Cat cat = model.getSelectedCat();
                    if (cat != null) {
                        if (cat.getUpgradeCounter() >= 3) {
                            maxUpgrade = true;
                        }
                        if (cat.getUpgradeCounter() < 3) {
                            cat.upgradeFireRate();
                            cat.upgradeTexture();
                        }
                    }
                updateButtonAppearance();
            }
        }
        });
    }

    @Override
    public void updateButtonAppearance() {
        TextureRegionDrawable appearance;
        if (maxUpgrade) {
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
