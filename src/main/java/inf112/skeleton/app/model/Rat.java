package inf112.skeleton.app.model;


public class Rat {
    private final int speed;
    private Coordinate pos;
    private int health;
    private int strength;
    

    public Rat(int health, int speed){
        this.health = health;
        this.speed = speed;
        this.pos = new Coordinate(10, -50); // Alle rottene spawner ovenfor brettet
    }

    public void takeDamage(int damage){
        health -= damage;
    }


    public int getStrength() {
        return strength;
    }

    public Coordinate getPosition() {
        return pos;
    }

    @Override
    public String toString(){
        return 
            "Rat " + " at position: " + pos + " with strength " + strength + " and health " + health;
    }
}