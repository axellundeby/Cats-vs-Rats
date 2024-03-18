package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat;

public class ShotgunCat extends Cat {

    public ShotgunCat() {
        super(20, 50, new Texture(Gdx.files.internal("hagleKatt.png")), new Texture(Gdx.files.internal("angryCat.png")), 3.0f);
    }

    @Override
    public ArrayList<Projectile> attack(LinkedList<Rat> rats) {
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
        return null;
    }

    @Override
    public Projectile shootAt(LinkedList<Rat> targets) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shootAt'");
    }
}
