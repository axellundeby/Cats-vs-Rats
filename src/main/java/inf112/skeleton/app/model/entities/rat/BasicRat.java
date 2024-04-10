package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BasicRat extends Rat {

    public BasicRat() {
        super(70, 2, 
        new Texture(Gdx.files.internal("rat1.png")), 
        new Texture(Gdx.files.internal("snow.png")),  
        new Texture(Gdx.files.internal("coin.png")),
        200,1);
    }
}
