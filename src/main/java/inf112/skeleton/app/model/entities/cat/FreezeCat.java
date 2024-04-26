package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.Rat;

public class FreezeCat extends Cat {

    public FreezeCat() {
        super(
            1,
            100, 
            Arrays.asList(new Texture(Gdx.files.internal("cats/Spill_Frysekatt1.png"))), 
            Arrays.asList(new Texture(Gdx.files.internal("cats/Spill_Frysekatt2_angrip.png")), new Texture(Gdx.files.internal("cats/Spill_Frysekatt3_angrip.png"))), 
            50.0f,
            1000);
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
        if (canAttack()) {
            triggerAttackImage(); 
            for (Rat rat : rats) {
                if (withinRange(rat)) {  
                    rat.freeze(Gdx.graphics.getDeltaTime()); 
                    rat.takeDamage(getStrength()); 
                }
            }
            resetAttackTimer(); 
        }
    }


    @Override
    public void upgradeDamage() {
       this.strength *= 1.25;

    }

    @Override
    public void upgradeRange() {
        this.range *= 1.25;
        this.circleUpdater();
    }

    @Override
    public void upgradeFireRate() {
        this.fireRate *= 0.75;
    }
}
