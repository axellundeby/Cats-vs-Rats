package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat;

public class FreezeCat extends Cat {

    public FreezeCat() {
        super(1, 100, new Texture(Gdx.files.internal("freezeCat.png")), new Texture(Gdx.files.internal("angryCat.png")), 20.0f);
    }

    @Override
    public ArrayList<Projectile> attack(LinkedList<Rat> rats) {
        if (canAttack()) {
            triggerAttackImage(); 
            for (Rat rat : rats) {
                rat.freeze(); 
            }
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
