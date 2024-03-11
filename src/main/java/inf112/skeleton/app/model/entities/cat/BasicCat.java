package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.ImageSwapper;

public class BasicCat extends Cat {

    public static Texture texture = new Texture(Gdx.files.internal("cat.png"));

    public BasicCat() {
        super(1, 100, texture, new Texture(Gdx.files.internal("Cat2.png")), 1.0f);
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
        rats.getFirst().takeDamage(getStrength());
        swapImage(PictureSwapper.ATTACK);
    }
}
