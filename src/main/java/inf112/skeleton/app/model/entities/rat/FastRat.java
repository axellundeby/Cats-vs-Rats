package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FastRat extends Rat {
    public FastRat() {
        super(50, 4, new Texture(Gdx.files.internal("coin.png")), 300, 2);
    }
}
