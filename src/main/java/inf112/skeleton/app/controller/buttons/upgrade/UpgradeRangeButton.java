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
    private static final String normalTexture= "range.png";
    private static final String noMoneyTexture = "noMoney.png";
    private static final String usedUpTexture= "Cat2.png";
    private static final String clickTexture= "coin.png";

    private int cost = 900;
    private int clicked = 0;

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
                if (model.getMoney() >= cost && clicked < 3) {
                    model.setMoney(model.getMoney() - cost); 
                    Cat cat = model.getSelectedCat();
                    if (cat != null)
                        cat.upgradeRange(); 

                    clicked++;
                }
                updateButtonAppearance();
            }
        });
    }



    @Override
    public void updateButtonAppearance() {
        TextureRegionDrawable appearance;
        if (clicked >= 3) {
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
