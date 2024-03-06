package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ShotgunCat extends Cat{

    public ShotgunCat(int strength, float range, Texture spriteImage) {
        super(20, 50, new Texture(Gdx.files.internal("cat.png")));
    }
}
