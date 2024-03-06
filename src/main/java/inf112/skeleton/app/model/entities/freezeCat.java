package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class freezeCat extends Cat{

    public freezeCat(int strength, float range, Texture spriteImage) {
        super(1, 100, new Texture(Gdx.files.internal("freezeCat.png")));
    }
    
}
