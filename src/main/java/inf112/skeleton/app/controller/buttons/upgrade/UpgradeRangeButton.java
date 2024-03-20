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

public class UpgradeRangeButton extends Buttons{
    private int clicked;

    public UpgradeRangeButton(SkadedyrModel model, Stage stage) {
        super(model, stage);
    }

    @Override
    protected void setupButton() {
        button = ButtonFactory.createImageButton("range.png", "coin.png");
        button.setSize(100, 100);
        button.setPosition(1050, 300);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                 if (clicked < 3) {
                    for (Cat cat : model.getCats()) {
                        cat.upgradeRange();
                   }
                    clicked++;
                }
                else {
                   updateButtonAppearance();
                }

            
        }
        });

    }

    @Override
    public void updateButtonAppearance() {
        button.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("rangeR.png")));
        button.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("rangeR.png")));



    }
    
}
