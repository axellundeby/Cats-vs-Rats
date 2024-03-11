package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.rat.Rat;

public class AngryCat extends Cat {

    public static Texture texture = new Texture(Gdx.files.internal("angryCat.png"));

    public AngryCat() {
        super(5, 150, texture, new Texture(Gdx.files.internal("Cat2.png")), 300.0f);
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
        // TODO Auto-generated method stub
    }
    
}
