package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class LibGDXResourceFactory implements GameResourceFactory {
    @Override
    public Texture getTexture(String path) {
        return new Texture(Gdx.files.internal(path));
    }

    @Override
    public Sound getSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }
}