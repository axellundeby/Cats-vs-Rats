package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FastRat extends Rat {
    public FastRat() {
        super(70, 2, new Texture(Gdx.files.internal("Spill_Rotte2.png")),200,1,new Texture(Gdx.files.internal("Spill_Rotte2_freezed.png")));
    }
}
