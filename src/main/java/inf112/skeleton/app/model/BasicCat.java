package inf112.skeleton.app.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BasicCat extends Cat {

    public BasicCat() {
        super(1, 5, new Texture(Gdx.files.internal("cat.png")));
        
    }
    
}
