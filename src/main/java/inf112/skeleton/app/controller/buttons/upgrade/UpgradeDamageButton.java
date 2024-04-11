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
    private static final String normalTexturePath = "damage.png";
    private static final String noMoneyTexturePath = "noMoney.png";
    private static final String usedUpTexturePath = "Cat2.png";
    private static final String clickTexturePath = "coin.png";

    private Texture normalTexture;
    private Texture noMoneyTexture;
    private Texture usedUpTexture;
    private Texture clickTexture;

    private TextureRegionDrawable normalAppearance;
    private TextureRegionDrawable noMoneyAppearance;
    private TextureRegionDrawable usedUpAppearance;
    private TextureRegionDrawable clickAppearance;

    private int cost = 800;
    private int clicked = 0;

    public UpgradeDamageButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
        loadTextures();
        setupButton();
    }

    private void loadTextures() {
        normalTexture = new Texture(normalTexturePath);
        noMoneyTexture = new Texture(noMoneyTexturePath);
        usedUpTexture = new Texture(usedUpTexturePath);
        clickTexture = new Texture(clickTexturePath);

        normalAppearance = new TextureRegionDrawable(new TextureRegion(normalTexture));
        noMoneyAppearance = new TextureRegionDrawable(new TextureRegion(noMoneyTexture));
        usedUpAppearance = new TextureRegionDrawable(new TextureRegion(usedUpTexture));
        clickAppearance = new TextureRegionDrawable(new TextureRegion(clickTexture));
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton(normalTexturePath, clickTexturePath);
        button.setSize(100, 100);
        button.setPosition(1050, 200);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (model.getMoney() >= cost && clicked < 3) {
                    model.setMoney(model.getMoney() - cost);
                    for (Cat cat : model.getCats()) {
                        cat.upgradeDamage();
                    }
                    clicked++;
                }
                updateButtonAppearance();
            }
        });
        
       // updateButtonAppearance(); 
    }

    @Override
    public void updateButtonAppearance() {
        if (clicked >= 3) {
            button.getStyle().up = usedUpAppearance;
        } else if (model.getMoney() < cost) {
            button.getStyle().up = noMoneyAppearance;
        } else {
            button.getStyle().up = normalAppearance;
        }

        button.getStyle().down = clickAppearance;
    }

    public void dispose() {
        normalTexture.dispose();
        noMoneyTexture.dispose();
        usedUpTexture.dispose();
        clickTexture.dispose();
    }
}
