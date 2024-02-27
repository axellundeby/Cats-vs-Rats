package inf112.skeleton.app.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BasicRat extends Rat {

    public BasicRat() {
        super(1, 5, new Texture(Gdx.files.internal("rat1.png")));
        
    }
    
}
