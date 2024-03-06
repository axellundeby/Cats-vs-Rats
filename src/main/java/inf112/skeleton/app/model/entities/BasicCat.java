package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BasicCat extends Cat {

    public static Texture texture = new Texture(Gdx.files.internal("cat.png"));

    public BasicCat() {
        super(1, 1 ,100, texture);

    }
    
}
