package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BasicRat extends Rat {
    Texture defaultTexture = new Texture(Gdx.files.internal("Spill_Rotte1.png"));
    Texture freezeTexture = new Texture(Gdx.files.internal("Spill_Rotte1_freezed.png"));
    public BasicRat() {
       super(70, 2, new Texture(Gdx.files.internal("Spill_Rotte1.png")),200,1,new Texture(Gdx.files.internal("Spill_Rotte1_freezed.png")));
    }
}
