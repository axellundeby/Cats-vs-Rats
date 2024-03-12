package inf112.skeleton.app.view.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public static TextButton createTextButton(String text, Color upColor, Color downColor, Color fontColor, BitmapFont font) {
        TextButtonStyle style = new TextButtonStyle();
        style.up = createDrawableFromColor(upColor, 200, 50);
        style.down = createDrawableFromColor(downColor, 200, 50);
        style.font = font;
        style.fontColor = fontColor;

        return new TextButton(text, style);
    }
}
