package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StrongRat extends Rat{

    public StrongRat() {
        super(500, 20, new Texture(Gdx.files.internal("rats/Spill_Rotte3.png")),500,3,new Texture(Gdx.files.internal("rats/Spill_Rotte3_freezed.png")),60, new Texture(Gdx.files.internal("coin.png")));
    }
}
