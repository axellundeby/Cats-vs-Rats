package inf112.skeleton.app.controller.buttons.upgrade;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.view.LibGDXResourceFactory;

public class UpgradeButtons implements IUpgradeButtons{
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
    private LibGDXResourceFactory resourceFactory;

    public UpgradeButtons(SkadedyrModel model) {
        this.model = model;
        buttonFactory = new ButtonFactory(model);
        resourceFactory = new LibGDXResourceFactory();
    }

    @Override
    public Button upgradeDamageButton() {
        this.upgradeDamageButton = buttonFactory.createUpgradeButton(20, resourceFactory.getTexture(damageTexture), resourceFactory.getTexture(clickTexture), 660, Y_POS,
                Cat::upgradeDamage);
        return upgradeDamageButton;
    }

    @Override
    public Button upgradeFireRateButton() {
        this.upgradeFireRateButton = buttonFactory.createUpgradeButton(20, resourceFactory.getTexture(fireRateTexture) , resourceFactory.getTexture(clickTexture), 825, Y_POS,
                Cat::upgradeFireRate);
        return upgradeFireRateButton;
    }

    @Override
    public Button upgradeRangeButton() {
        this.upgradeRangeButton = buttonFactory.createUpgradeButton(20, resourceFactory.getTexture(rangeTexture) , resourceFactory.getTexture(clickTexture), 990, Y_POS,
                Cat::upgradeRange);
        return upgradeRangeButton;
    }

    @Override
    public void updateButtonAppearance() {
        updateUpgradeButtonAppearances(upgradeDamageButton, resourceFactory.getTexture(damageTexture));
        updateUpgradeButtonAppearances(upgradeFireRateButton, resourceFactory.getTexture(fireRateTexture));
        updateUpgradeButtonAppearances(upgradeRangeButton, resourceFactory.getTexture(rangeTexture));

    }

    private void updateUpgradeButtonAppearances(Button button, Texture upgradeTexture) {
        Cat selectedCat = model.getSelectedCat();
        TextureRegionDrawable newDrawable = null;
        if (selectedCat != null) {

            if (selectedCat.getUpgradeCount() >= 4) {
                newDrawable = new TextureRegionDrawable(new TextureRegion(resourceFactory.getTexture(usedUpTexture)));
            } else if (model.getMoney() < cost) {
                newDrawable = new TextureRegionDrawable(new TextureRegion(resourceFactory.getTexture(noMoneyTexture)));
            } else {
                newDrawable = new TextureRegionDrawable(new TextureRegion(upgradeTexture));
            }
        }
        button.getStyle().up = newDrawable;
        button.getStyle().down = newDrawable;

    }

}
