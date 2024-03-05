package inf112.skeleton.app.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.model.entities.BasicCat;
import inf112.skeleton.app.model.entities.BasicRat;
import inf112.skeleton.app.model.entities.Cat;
import inf112.skeleton.app.model.entities.Rat;
import inf112.skeleton.app.model.entities.Rat.Direction;

public class SkadedyrModel implements ISkadedyrModel {
    private ArrayList<Cat> cats;
    private ArrayList<Rat> aliveRats;
    private int lives = 5;
    private int money = 3000;
    private int points = 0;
    private int level = 0;
    private int ratsSpawned;
    private int ratLimitPerLevel = 10;
    private Rat testRat;

    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.aliveRats = new ArrayList<>();
    }

    @Override
    public void addCat(Cat cat) {
        cats.add(cat);
    }

    @Override
    public void addRat(Rat rat) {
        aliveRats.add(rat);
    }

    @Override
    public ArrayList<Cat> getCats() {
        return cats;
    }

    @Override
    public ArrayList<Rat> getRats() {
        return aliveRats;
    }

    @Override
    public void moveRats() {
        for (Rat rat : aliveRats) {
            rat.move();
        }
    }

    public void gameOver() {
        Gdx.app.exit(); //jacob skjerm
    }

    public int getRatLimitPerLevel() {
        return ratLimitPerLevel;
    }

    public int getRatsSpawned() {
        return ratsSpawned;
    }

    public int getMoney(){
        return money;
    }

    public int getLevel(){
        return level;
    }

    public int getPoints(){
        return points;
    }

    public void spawnRats() {
        this.testRat = new BasicRat();
        addRat(testRat);
        ratsSpawned++;
    }

    //hvor kalle på denne?
    public void everyRatDead() {
        if (aliveRats.isEmpty()) {
            level++;
            //runden er over 
            //nextRound();
        }
    }

    // public void nextRound(){
    //     ratLimitPerLevel * 1.2;
    // }

    public int getLives() {
        for (Rat rat : aliveRats) {
            if (rat.getDirection() == Direction.OUT) {
                aliveRats.remove(rat);
                return lives--;
            }
        }
        if (lives <= 0) {
           gameOver();
        }
        return lives;
    }

    public HashMap<Cat, LinkedList<Rat>> attackRatsForEachCat() {
        HashMap<Cat, LinkedList<Rat>> attackMap = new HashMap<>();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = new LinkedList<>();
            for (Rat rat : aliveRats) {
                if (cat.withinRange(rat)) {
                    attackableRats.addLast(rat);
                }
            }
            attackMap.put(cat, attackableRats);
        }
        return attackMap;
    }
    
    public void attackRat() {
        HashMap<Cat, LinkedList<Rat>> attackMap = attackRatsForEachCat();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (attackableRats != null && !attackableRats.isEmpty()) { 
                attackableRats.getFirst().takeDamage(cat.getStrength());
                if (attackableRats.getFirst().isKilled()) {
                    money += 1000; 
                    points += 100; 
                }
            }
        }
    }
    public void newCat(int mouseX, int mouseY) {
        Cat catTest = new BasicCat();
        catTest.setPos(mouseX, mouseY);
        addCat(catTest);
        System.out.println(mouseX + ", " + mouseY);
    }

}
