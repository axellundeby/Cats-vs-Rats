package inf112.skeleton.app.model;

public class Cat {

    private int strength;
    private final double range;
    private Coordinate pos;

    public Cat(int strength, double range){
        this.strength = strength;
        this.range = range;
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
    
}
