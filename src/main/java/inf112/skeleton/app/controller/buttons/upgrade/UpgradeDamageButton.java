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

public class UpgradeDamageButton extends Buttons {
    private Texture normalTexture;
    private Texture noMoneyTexture;
    private Texture usedUpTexture;
    private Texture clickTexture;

    private static final int MAX_UPGRADE = 4;
    private static final int MAX_UPGRADE_PER = 3;
    private int cost = 0;

    public UpgradeDamageButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
        normalTexture = new Texture("buttons_game/damage.png");
        noMoneyTexture = new Texture("buttons_game/noMoney.png");
        usedUpTexture = new Texture("ikkeTilgjengelig.png");
        clickTexture = new Texture("coin.png");
        setupButton();
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton(
            new TextureRegionDrawable(new TextureRegion(normalTexture)),
            new TextureRegionDrawable(new TextureRegion(clickTexture))
        );
        button.setSize(100, 100);
        button.setPosition(800, 50);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Cat selectedCat = model.getSelectedCat();
                if (selectedCat != null && selectedCat.getUpgradeCounter() <= MAX_UPGRADE) {
                    if (model.getMoney() >= cost) {
                        selectedCat.upgradeDamage();
                        selectedCat.upgradeTexture();
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
            appearance = new TextureRegionDrawable(new TextureRegion(usedUpTexture));
        } else if (model.getMoney() < cost) {
            appearance = new TextureRegionDrawable(new TextureRegion(noMoneyTexture));
        } else {
            appearance = new TextureRegionDrawable(new TextureRegion(normalTexture));
        }

        button.getStyle().up = appearance;
        button.getStyle().down = new TextureRegionDrawable(new TextureRegion(clickTexture));
    }

    // @Override
    // public void dispose() {
    //     normalTexture.dispose();
    //     noMoneyTexture.dispose();
    //     usedUpTexture.dispose();
    //     clickTexture.dispose();
    // }
}
