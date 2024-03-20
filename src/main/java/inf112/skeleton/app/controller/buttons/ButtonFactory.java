package inf112.skeleton.app.controller.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonFactory {

    public static ImageButton createImageButton(String upImagePath, String downImagePath) {
        Drawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(upImagePath))));
        Drawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(downImagePath))));
        ImageButtonStyle style = new ImageButtonStyle();
        style.up = upDrawable;
        style.down = downDrawable;

        return new ImageButton(style);
    }



}
