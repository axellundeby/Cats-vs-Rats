package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LibGDXResourceFactory implements GameResourceFactory {
    @Override
    public Texture getTexture(String path) {
        return new Texture(Gdx.files.internal(path));
    }
}


