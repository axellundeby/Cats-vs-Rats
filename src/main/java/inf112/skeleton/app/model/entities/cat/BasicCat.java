package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.rat.Rat;

public class BasicCat extends Cat {
    public BasicCat() {
        super(10, 100, new Texture(Gdx.files.internal("cat.png")), new Texture(Gdx.files.internal("Cat2.png")), 1.0f);
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
        if (canAttack()) {
            triggerAttackImage();
            rats.getFirst().takeDamage(getStrength());
            swapImage(PictureSwapper.ATTACK);
            resetAttackTimer();
        }
    }
}
