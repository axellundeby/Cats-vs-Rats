package inf112.skeleton.app.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Cat {

    private int strength;
    private double range;
    private Coordinate pos;
	private Texture spriteImage;

    public Cat(int strength, double range, int type){
        this.strength = strength;
        this.range = range;
        this.spriteImage = null;
    }


    public void shoot(Rat target){

        if (withinRange(target))
            target.takeDamage(strength);
    }
    

    private boolean withinRange(Rat target) {
        Coordinate ratPos = target.getPosition();
        return range >= distance(ratPos, pos);
    }

    private double distance(Coordinate p1, Coordinate p2) {
        double x_dist = Math.abs(p1.x() - p2.x());
        double y_dist = Math.abs(p1.y() - p2.y());
        return Math.pow(Math.pow(x_dist, 2.0) + Math.pow(y_dist, 2.0), 0.5);
    }

    public String name() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'name'");
    }

    public int getStrength() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStrength'");
    }

    public Coordinate getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocation'");
    }

    @Override
    public String toString() {
        return
            "Cat " + name() + " at position: " + pos + " with strength " + strength + " and range " + range;
    }
    
}
