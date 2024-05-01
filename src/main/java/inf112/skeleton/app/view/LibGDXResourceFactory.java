package inf112.skeleton.app.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.HashMap;

public class LibGDXResourceFactory implements GameResourceFactory {
  

    @Override
    public Texture getTexture(String path) {
        return new Texture(Gdx.files.internal(path));
    }

    @Override
    public Sound getSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }

    @Override
    public Stage createStage() {
        return new Stage();
    }

    @Override
    public SpriteBatch createSpriteBatch() {
        return new SpriteBatch();
    }

    @Override
    public ShapeRenderer createShapeRenderer() {
        return new ShapeRenderer();
    }

    @Override
    public BitmapFont createFont(Color color) {
        BitmapFont font = new BitmapFont();
        font.setColor(color);
        return font;
    }

    @Override
    public void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void enableBlending() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void disableBlending() {
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
