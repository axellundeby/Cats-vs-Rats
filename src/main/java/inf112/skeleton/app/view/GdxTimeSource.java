package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;

public class GdxTimeSource implements TimeSource {
    @Override
    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}

