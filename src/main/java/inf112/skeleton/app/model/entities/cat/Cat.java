package inf112.skeleton.app.model.entities.cat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.Rat;

public class Cat {

    private int strength;
    private float range;
    private Vector2 pos;
	private Texture spriteImage;
	private Rectangle spriteRect;
    private Circle rangeCircle;
    private int size;
    private int halfSize;

    public Cat(int strength, float range, Texture spriteImage){
        this.strength = strength;
        this.range = range;
        this.spriteImage = spriteImage;
        this.pos = new Vector2();
        this.size = 60;

        this.halfSize = size/2;

        this.spriteRect = new Rectangle(pos.x-halfSize, pos.y-halfSize, size, size);
        this.rangeCircle = new Circle(pos, range);
    }


    public void shoot(Rat target){
        if (withinRange(target))
            target.takeDamage(strength);
    }
    
    public void setPos(int x, int y){
        pos.x = x;
        pos.y = y;
        this.spriteRect = new Rectangle(pos.x-halfSize, pos.y-halfSize, size, size);
        this.rangeCircle = new Circle(pos, range);

    }
    public boolean withinRange(Rat target) {
        Vector2 ratPos = target.getPosition();
        return range >= distance(ratPos, pos);
    }

    private double distance(Vector2 p1, Vector2 p2) {
        double x_dist = Math.abs(p1.x - p2.x);
        double y_dist = Math.abs(p1.y - p2.y);
        return Math.pow(Math.pow(x_dist, 2.0) + Math.pow(y_dist, 2.0), 0.5);
    }
    
    public Texture getTexture(){
        return spriteImage;
    }
    public Rectangle getRectangle(){
        return spriteRect;
    }

    public Circle getRangeCircle(){
        return rangeCircle;
    }
    

    public int getStrength() {
        return strength;
    }

    public Vector2 getPosition() {
        return pos;
    }

    @Override
    public String toString() {
        return
            "Cat at position: " + pos + " with strength " + strength + " and range " + range;
    
    }
}
