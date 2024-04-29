package inf112.skeleton.app.model.entities.cat;

import java.util.Arrays;
import java.util.LinkedList;

import com.badlogic.gdx.audio.Sound;

import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.view.GameResourceFactory;

public class ShotgunCat extends Cat {
    private GameResourceFactory resourceFactory;

    public ShotgunCat(GameResourceFactory resourceFactory) {
        super(
            10,
            200,
            Arrays.asList(resourceFactory.getTexture("cats/Spill_Skytekatt1.png"),resourceFactory.getTexture("cats/Spill_Skytekatt2.png"),resourceFactory.getTexture("cats/Spill_Skytekatt3.png")),
            Arrays.asList(resourceFactory.getTexture("cats/Spill_Skytekatt1_angrip.png"),resourceFactory.getTexture("cats/Spill_Skytekatt2_angrip.png"),resourceFactory.getTexture("cats/Spill_Skytekatt3_angrip.png")),
            5.0f,
            200);
            this.resourceFactory = resourceFactory;
    }

    @Override
    public void attack(LinkedList<Rat> rats) {
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

    @Override
    public void playAttackSound() {
        Sound s = this.resourceFactory.getSound("sound/gunshot.mp3");
        s.play(0.6f);
    }

}
