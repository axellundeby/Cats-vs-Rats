package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.rat.Rat;

public class ShotgunCat extends Cat {

    public ShotgunCat() {
        super(20, 50, new Texture(Gdx.files.internal("hagleKatt.png")), new Texture(Gdx.files.internal("SinnaKatt.png")), 3.0f);
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
        if (canAttack()) {
            int attacks = 3;
            for (int i = 0; i < rats.size() && attacks > 0; i++) {
                Rat targetRat = rats.get(i);
                targetRat.takeDamage(getStrength());
                attacks--;
            }
            triggerAttackImage();
            resetAttackTimer();
        }
    }
}
