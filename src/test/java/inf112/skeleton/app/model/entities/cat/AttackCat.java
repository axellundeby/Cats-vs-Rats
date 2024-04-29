package inf112.skeleton.app.model.entities.cat;
import java.util.LinkedList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import java.util.List;
import inf112.skeleton.app.model.entities.rat.Rat;

public class AttackCat extends Cat {

    public AttackCat(int strength, int range, List<Texture> defaultImages, List<Texture> attackImages, float fireRate, int cost) {
        super(strength, range, defaultImages, attackImages, fireRate, cost);
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
        //testing for sound in the other cat objects
    }

   

}
