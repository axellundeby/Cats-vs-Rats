package inf112.skeleton.app;


public class Rat{
    private final int speed;
    private Coordinate pos;
    private int health;
    private int size;

    public Rat(int health, int speed){
        this.health = health;
        this.speed = speed;
    }

    public void takeDamage(int damage){
        health -= damage;
    }
}