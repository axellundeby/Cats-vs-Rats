package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.rat.Rat;

public class FreezeCat extends Cat{

    public FreezeCat() {
        super(1, 100, new Texture(Gdx.files.internal("freezeCat.png")), new Texture(Gdx.files.internal("Cat2.png")));
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
        for (Rat rat : rats) {
            rat.freeze();
        }
    }
    
}
