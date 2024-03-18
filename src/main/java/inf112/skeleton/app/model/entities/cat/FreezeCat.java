package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat;

public class FreezeCat extends Cat {

    public FreezeCat() {
        super(1, 100, new Texture(Gdx.files.internal("freezeCat.png")), new Texture(Gdx.files.internal("angryCat.png")), 20.0f);
    }

    @Override
    public ArrayList<Projectile> attack(LinkedList<Rat> rats) {
        ArrayList<Projectile> projectileList = new ArrayList<>();
        if (canAttack()) {
            triggerAttackImage(); 
            for (Rat rat : rats) {
                //når prosejektilet har truffet.
                projectileList.add(shootAt(rats));
                rat.freeze(); 
                rat.takeDamage(getStrength());
            }
            resetAttackTimer(); 
        }
        return projectileList;
    }

    @Override
    public Projectile shootAt(LinkedList<Rat> targets) {
        for (Rat target : targets) {
            Vector2 direction = new Vector2(target.getPosition().x - getPosition().x, target.getPosition().y - getPosition().y);
            return new Projectile(new Vector2(this.getPosition()), direction, 100,  new Texture("snowProjectile.png"));
        }
        return null;
    }    
}
