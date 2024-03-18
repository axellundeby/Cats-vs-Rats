package inf112.skeleton.app.model.entities.cat;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.Rat;

public class AngryCat extends Cat {

    public AngryCat() {
        super(5, 150, new Texture(Gdx.files.internal("angryCat.png")), new Texture(Gdx.files.internal("angryCat.png")), 300.0f);
    }

    @Override
    public ArrayList<Projectile> attack(LinkedList<Rat> rats) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public Projectile shootAt(LinkedList<Rat> targets) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shootAt'");
    }
    
}
