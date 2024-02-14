package inf112.skeleton.app.model;


public class Rat implements IEntity{
    private final int speed;
    private Coordinate pos;
    private int health;
    private int size;
    private int strength;

    public Rat(int health, int speed){
        this.health = health;
        this.speed = speed;
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    @Override
    public String name() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'name'");
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public Coordinate getPosition() {
        return pos;
    }

    @Override
    public String toString(){
        return 
            "Rat " + name() + " at position: " + pos + " with strength " + strength + " and health " + health;
    }
}