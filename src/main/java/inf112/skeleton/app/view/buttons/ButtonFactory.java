package inf112.skeleton.app.view.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonFactory {

    public static Drawable createDrawableFromColor(Color color, int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap); // Don't forget to dispose of the Pixmap!
        pixmap.dispose();

        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    // In ButtonFactory.java
    public static ImageButton createImageButton(String upImagePath, String downImagePath) {
        Drawable upDrawable = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(upImagePath))));
        Drawable downDrawable = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(downImagePath))));

        ImageButtonStyle style = new ImageButtonStyle();
        style.up = upDrawable;
        style.down = downDrawable;

        return new ImageButton(style);
    }

}
