package inf112.skeleton.app.controller.buttons.upgrade;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.ICat;

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
    private static final String usedUpTexture = "buttons_game/Spill_Exit.png";
    private static final String clickTexture = "coin.png";

    private ButtonFactory buttonFactory;

    public UpgradeButtons(SkadedyrModel model) {
        this.model = model;
        buttonFactory = new ButtonFactory(model);
    }

    public Button upgradeDamageButton() {
        this.upgradeDamageButton = buttonFactory.createUpgradeButton(20, damageTexture, clickTexture, 660, Y_POS,
                ICat::upgradeDamage);
        return upgradeDamageButton;
    }

    public Button upgradeFireRateButton() {
        this.upgradeFireRateButton = buttonFactory.createUpgradeButton(20, fireRateTexture, clickTexture, 825, Y_POS,
        ICat::upgradeFireRate);
        return upgradeFireRateButton;
    }

    public Button upgradeRangeButton() {
        this.upgradeRangeButton = buttonFactory.createUpgradeButton(20, rangeTexture, clickTexture, 990, Y_POS,
        ICat::upgradeRange);
        return upgradeRangeButton;
    }

    public void updateButtonAppearance() {
        updateUpgradeButtonAppearances(upgradeDamageButton, damageTexture);
        updateUpgradeButtonAppearances(upgradeFireRateButton, fireRateTexture);
        updateUpgradeButtonAppearances(upgradeRangeButton, rangeTexture);

    }

    private void updateUpgradeButtonAppearances(Button button, String upgradeTexture) {
        ICat selectedCat = model.getSelectedCat();
        TextureRegionDrawable newDrawable = null;
        if (selectedCat != null) {

            if (selectedCat.getUpgradeCount() >= 4) {
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
