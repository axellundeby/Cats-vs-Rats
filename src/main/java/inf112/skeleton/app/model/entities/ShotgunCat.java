package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ShotgunCat extends Cat{

    public ShotgunCat() {
        super(1, 2 ,100,new Texture(Gdx.files.internal("hagleKatt.png")));
    }
}
