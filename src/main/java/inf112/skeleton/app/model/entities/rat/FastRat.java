package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FastRat extends Rat {
    public FastRat() {
        super(40, 40, new Texture(Gdx.files.internal("rats/Spill_Rotte2.png")),300,2,new Texture(Gdx.files.internal("rats/Spill_Rotte2_freezed.png")),35, new Texture(Gdx.files.internal("coin.png")));
    }
}
