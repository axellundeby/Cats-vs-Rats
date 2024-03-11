package inf112.skeleton.app.model.entities.cat;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.rat.Rat;

public class ShotgunCat extends Cat {

    public ShotgunCat() {
        super(20, 50, new Texture(Gdx.files.internal("hagleKatt.png")));
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
        int attacks = 3;
        int ratsCount = rats.size();
        for (int i = 0; i < ratsCount && attacks > 0; i++) {
            Rat targetRat = rats.get(i);
            int attacksOnThisRat = Math.min(attacks, 3 - i);
            for (int j = 0; j < attacksOnThisRat; j++) {
                if (targetRat != null) {
                    targetRat.takeDamage(getStrength());
                    attacks--;
                }
            }
        }
    }
}
