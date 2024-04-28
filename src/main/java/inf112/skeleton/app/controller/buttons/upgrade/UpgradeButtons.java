package inf112.skeleton.app.controller.buttons.upgrade;

import java.util.function.Consumer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;

public class UpgradeButtons {
    private SkadedyrModel model;

    private Button upgradeDamageButton;
    private Button upgradeFireRateButton;
    private Button upgradeRangeButton;

    private int cost = 0;
    private static final int Y_POS = 70;

    private static final String damageTexture = "buttons_game/damage.png";
    private static final String fireRateTexture = "buttons_game/firerate.png";
    private static final String rangeTexture = "buttons_game/range.png";

    private static final String noMoneyTexture = "buttons_game/noMoney.png";
    private static final String usedUpTexture = "buttons_game/angryCat.png";
    private static final String clickTexture = "coin.png";


    public UpgradeButtons(SkadedyrModel model) {
        this.model = model;
    }

    private Button createUpgradeButton(int cost, String textureUp, int xPosition, Consumer<Cat> upgradeAction) {


        Button upgradeButton = ButtonFactory.createImageButton(textureUp, "coin.png");

        upgradeButton.setSize(160, 80);

        upgradeButton.setPosition(xPosition, Y_POS);

        upgradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Cat selectedCat = model.getSelectedCat();

                if (selectedCat != null && model.getMoney() >= cost && selectedCat.getUpgradeCounter() < 4) {
                    upgradeAction.accept(selectedCat);
                    selectedCat.setUpgradeCounter(selectedCat.getUpgradeCounter() + 1);
                    model.setMoney(model.getMoney() - cost);
                    updateButtonAppearance();
                }
            }
        });

        return upgradeButton;
    }

    public Button upgradeDamageButton() {
        this.upgradeDamageButton = createUpgradeButton(2000, damageTexture, 650, Cat::upgradeDamage);
        return upgradeDamageButton;
    }

    public Button upgradeFireRateButton() {
        this.upgradeFireRateButton = createUpgradeButton(2000, fireRateTexture, 820, Cat::upgradeFireRate);
        return upgradeFireRateButton;
    }

    public Button upgradeRangeButton() {
        this.upgradeRangeButton = createUpgradeButton(200, rangeTexture, 990, Cat::upgradeRange);
        return upgradeRangeButton;
    }

    public void updateButtonAppearance() {

        updateUpgradeButtonAppearances(upgradeDamageButton, damageTexture);
        updateUpgradeButtonAppearances(upgradeFireRateButton, fireRateTexture);
        updateUpgradeButtonAppearances(upgradeRangeButton, rangeTexture);

    }

    private void updateUpgradeButtonAppearances(Button button, String upgradeTexture) {
        Cat selectedCat = model.getSelectedCat();
        TextureRegionDrawable newDrawable = null;
        if (selectedCat != null) {

            if (selectedCat.getUpgradeCounter() >= 4) {
                newDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(usedUpTexture)));
            } else if (model.getMoney() < cost) {
                newDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(noMoneyTexture)));
            } else {
                newDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(upgradeTexture)));
            }
        }
        button.getStyle().up = newDrawable;
        button.getStyle().down = newDrawable;

        
        
    }

}
