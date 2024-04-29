package inf112.skeleton.app.view;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface GameResourceFactory {
    Texture getTexture(String path);
    Sound getSound(String path);
}


