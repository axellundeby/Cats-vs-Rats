package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat;

public class ShotgunCat extends Cat {

    public ShotgunCat() {
        super(
            10,
            200,
            Arrays.asList(new Texture(Gdx.files.internal("cats/Spill_Skytekatt1.png")),new Texture(Gdx.files.internal("cats/Spill_Skytekatt2.png")),new Texture(Gdx.files.internal("cats/Spill_Skytekatt3.png"))),
            Arrays.asList(new Texture(Gdx.files.internal("cats/Spill_Skytekatt1_angrip.png")),new Texture(Gdx.files.internal("cats/Spill_Skytekatt2_angrip.png")),new Texture(Gdx.files.internal("cats/Spill_Skytekatt3_angrip.png"))),
            5.0f,
            200);
    }

    @Override
    public ArrayList<Projectile> attack(LinkedList<Rat> rats) {
        ArrayList<Projectile> projectileList = new ArrayList<>();
        if (canAttack()) {
            triggerAttackImage();
            //projectileList.add(shootAt(rats));
            rats.getFirst().takeDamage(getStrength());
            resetAttackTimer();
        }
        return projectileList;
    }

    @Override
    public Projectile shootAt(LinkedList<Rat> targets) {

       for (Rat target : targets) {
            Vector2 direction = new Vector2(target.getPosition().x - getPosition().x, target.getPosition().y - getPosition().y);
            return new Projectile(new Vector2(this.getPosition()), direction, 100,  new Texture("b.png"));
        }
        return null;  
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
