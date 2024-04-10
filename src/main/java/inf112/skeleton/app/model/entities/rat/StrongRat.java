package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StrongRat extends Rat{
    Texture texture = new Texture(Gdx.files.internal("rat1.png"));
    public StrongRat() {
        super(200, 2, new Texture(Gdx.files.internal("Spill_Rotte3.png")),500,3,new Texture(Gdx.files.internal("Spill_Rotte3_freezed.png")),50);
    }
}
