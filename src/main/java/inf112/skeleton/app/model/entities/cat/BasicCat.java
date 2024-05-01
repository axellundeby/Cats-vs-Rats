package inf112.skeleton.app.model.entities.cat;
import java.util.Arrays;
import java.util.LinkedList;
import com.badlogic.gdx.audio.Sound;

import inf112.skeleton.app.model.entities.rat.IRat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.view.GameResourceFactory;


public class BasicCat extends Cat {
    private GameResourceFactory resourceFactory;
      public BasicCat(GameResourceFactory resourceFactory) {
        super(50, 100, 
            Arrays.asList(resourceFactory.getTexture("cats/Spill_Kosekatt1.png"), resourceFactory.getTexture("cats/Spill_Kosekatt2.png"), resourceFactory.getTexture("cats/Spill_Kosekatt3.png")), 
            Arrays.asList(resourceFactory.getTexture("cats/Spill_Kosekatt1_angrip.png"), resourceFactory.getTexture("cats/Spill_Kosekatt3_angrip.png"), resourceFactory.getTexture("cats/Spill_Kosekatt3_angrip.png")), 
            25.0f, 200);

            this.resourceFactory = resourceFactory;
    }

    @Override
    public void attack(LinkedList<IRat> rats) {
        if (canAttack()) {
            triggerAttackImage();
            playAttackSound();
            rats.getFirst().takeDamage(getStrength());
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

    public void playAttackSound() {
        Sound s = this.resourceFactory.getSound("sound/fart.mp3"); 
        s.play(0.6f);
    }
    

}
