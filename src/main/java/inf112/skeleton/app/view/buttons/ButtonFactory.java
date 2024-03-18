package inf112.skeleton.app.view.buttons;

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

    public static TextButton createTextButton(String text, Color upColor, Color downColor, Color fontColor, BitmapFont font) {
        TextButtonStyle style = new TextButtonStyle();
        style.up = createDrawableFromColor(upColor, 200, 50);
        style.down = createDrawableFromColor(downColor, 200, 50);
        style.font = font;
        style.fontColor = fontColor;

        return new TextButton(text, style);
    }

    public static ImageButton createImageButton(String upImagePath, String downImagePath) {
        // Create the drawables for the up and down states
        Drawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(upImagePath)));
        Drawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(downImagePath)));

        // Create and set up the style for the ImageButton
        ImageButtonStyle style = new ImageButtonStyle();
        style.up = upDrawable; // Set the image to be used when the button is not pressed
        style.down = downDrawable; // Set the image to be used when the button is pressed

        // Create the ImageButton with the specified style
        return new ImageButton(style);
    }


}
