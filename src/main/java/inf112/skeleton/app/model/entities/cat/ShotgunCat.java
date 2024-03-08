package inf112.skeleton.app.model.entities.cat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ShotgunCat extends Cat{

    public ShotgunCat() {
        super(20, 50, new Texture(Gdx.files.internal("hagleKatt.png")));
    }
}
