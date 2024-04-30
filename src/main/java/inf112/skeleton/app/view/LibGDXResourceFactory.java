package inf112.skeleton.app.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

public class LibGDXResourceFactory implements GameResourceFactory {
    private HashMap<String, Texture> textures = new HashMap<>();

    @Override
    public Texture getTexture(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        if (!textures.containsKey(path)) {
            textures.put(path, new Texture(Gdx.files.internal(path)));
        }
        return textures.get(path);
    }
    

    @Override
    public Sound getSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }
}