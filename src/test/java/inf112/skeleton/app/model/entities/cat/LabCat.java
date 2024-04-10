package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat;

public class LabCat extends Cat {

    public LabCat(int strength, float range, Texture defaultImage, Texture attackImage, float fireRate, int cost) {
        super(strength, range, defaultImage, attackImage, fireRate, cost);
    }

    @Override
    public ArrayList<Projectile> attack(LinkedList<Rat> rats) {
        return new ArrayList<>();
    }

    @Override
    public void upgradeDamage() {
        this.strength += 10;
    }

    @Override
    public void upgradeRange() {
        this.range += 5.0f;
    }

    @Override
    public void upgradeFireRate() {
        this.fireRate -= 0.1f;
    }

    @Override
    public Projectile shootAt(LinkedList<Rat> targets) {
        return new Projectile(new Vector2(), new Vector2(), 0.1f, getTexture());
    }

}
