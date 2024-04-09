package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BossRat extends Rat{
    Texture texture = new Texture(Gdx.files.internal("rat1.png"));
    public BossRat() {
       super(70, 2, new Texture(Gdx.files.internal("rat1.png")),200,1);
        //super(health, 2, texture, bounty, points); 
    }
}
