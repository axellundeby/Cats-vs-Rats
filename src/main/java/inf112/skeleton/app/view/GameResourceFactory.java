package inf112.skeleton.app.view;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface GameResourceFactory {
    Texture getTexture(String path);
    Sound getSound(String path);
    Stage createStage();
    SpriteBatch createSpriteBatch();
    ShapeRenderer createShapeRenderer();
    BitmapFont createFont(Color color);
    void clearScreen(Color color);
    void enableBlending();
    void disableBlending();
}
