package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StrongRat extends Rat{
    Texture texture = new Texture(Gdx.files.internal("rat1.png"));
    public StrongRat() {
        super(70, 2, new Texture(Gdx.files.internal("Spill_Rotte3.png")),200,1,new Texture(Gdx.files.internal("Spill_Rotte3_freezed.png")));
        //super(health, 2, texture, bounty, points); 
    }
}
