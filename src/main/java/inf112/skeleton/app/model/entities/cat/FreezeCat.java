package inf112.skeleton.app.model.entities.cat;

import java.util.Arrays;
import java.util.LinkedList;
import com.badlogic.gdx.audio.Sound;
import inf112.skeleton.app.model.entities.rat.IRat;
import inf112.skeleton.app.view.GameResourceFactory;

public class FreezeCat extends Cat {
    private GameResourceFactory resourceFactory;
    
    public FreezeCat(GameResourceFactory resourceFactory) {
        super(
            5,
            100, 
            Arrays.asList(resourceFactory.getTexture("cats/Spill_Frysekatt1.png")),
            Arrays.asList(resourceFactory.getTexture("cats/Spill_Frysekatt2_angrip.png"),resourceFactory.getTexture("cats/Spill_Frysekatt3_angrip.png")), 
            50.0f,
            1000);
            this.resourceFactory = resourceFactory;
    }

    @Override
    public void attack(LinkedList<IRat> rats) {
        if (canAttack()) {
            triggerAttackImage();
            playAttackSound();
            for (IRat rat : rats) {
                rat.setFrozen();
                rat.takeDamage(getStrength());
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

    @Override
    public void playAttackSound() {
        Sound s = this.resourceFactory.getSound("sound/ice.mp3");
        s.play(0.6f);
    }
}
