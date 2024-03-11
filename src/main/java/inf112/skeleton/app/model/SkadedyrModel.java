package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;

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
    private float spawnTimer = 0;
    private float freezeTimer = 0;
    private int ratSpawnDelay = 5;
    private int ratFreezeDelay = 5;

    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.aliveRats = new ArrayList<>();
    }

    public void clockTick() {
        // System.out.println(intervalSeconds);
        // This code will be executed every n seconds
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        // model.mousePos();
        moveRats();
        attackRat();
        attackRatsForEachCat();
        upDateTexture();
        unfreezeRats();

        spawnTimer += 0.05;
        if (spawnTimer > ratSpawnDelay && getRatsSpawned() < getRatLimitPerLevel()) {
            spawnRats();
            spawnTimer = 0;
        }

        freezeTimer += 0.05;
        if (spawnTimer > ratFreezeDelay) {
            unfreezeRats();
            freezeTimer = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            spawnRats();
        }

        if (Gdx.input.isTouched()) { // check for mouse click
            newCat(mouseX, 842 - mouseY);

        }

        for (Rat rat : getRats()) {
            rat.addTime();
        }
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
        Gdx.app.exit(); // jacob skjerm
    }

    public int getRatLimitPerLevel() {
        return ratLimitPerLevel;
    }

    public int getRatsSpawned() {
        return ratsSpawned;
    }

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    public void spawnRats() {
        this.testRat = new BasicRat();
        addRat(testRat);
        ratsSpawned++;
    }

    // hvor kalle på denne?
    public void everyRatDead() {
        if (aliveRats.isEmpty()) {
            level++;
            // runden er over
            // nextRound();
        }
    }

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
                cat.attack(attackableRats);
                if (attackableRats.getFirst().isKilled()) {
                    money += 1000;
                    points += 100;
                }
            }
        }
    }

    public void upDateTexture(){
        for (Cat cat : cats) {
            for (Rat rat : aliveRats) {
                if (cat.withinRange(rat)) {
                    cat.swapImage(Cat.PictureSwapper.ATTACK);
                }
                else {
                    cat.swapImage(Cat.PictureSwapper.DEFAULT);
                }
            }
        }
    }

    private void unfreezeRats() {
        for (Rat rat : aliveRats) {
            if (rat.isFrozen()) {
                rat.unfreeze();
            }
        }
    }

    

    public void transaction() {
        int priceForBasicCat = 1000;
        int priceForFreezeCat = 2000;
        int priceForShotgunCat = 3000;

        if (priceForBasicCat <= money) { // og katta er kjøpt
            money -= priceForBasicCat;
        } else if (priceForFreezeCat <= money) { // og katta er kjøpt
            money -= priceForFreezeCat;
        } else if (priceForShotgunCat <= money) { // og katta er kjøpt
            money -= priceForShotgunCat;
        }
    }

    public void newCat(int mouseX, int mouseY) {
        Cat gangsta = new ShotgunCat();
        Cat froze = new FreezeCat();
        Cat meow = new BasicCat();
        froze.setPos(mouseX, mouseY);
        addCat(froze);
    }

}
