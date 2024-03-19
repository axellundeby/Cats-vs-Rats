package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.Projectile;
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
    private int ratSpawnDelay = 5;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private CatMenu catMenu;

    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.aliveRats = new ArrayList<>();
        this.catMenu = new CatMenu();
    }

    public void clockTick() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        //chat melder at denne skal være der frames oppdateres
        for (Cat cat : cats) {
            cat.updateAnimation(deltaTime);
        }
        // System.out.println(intervalSeconds);
        // This code will be executed 20 times per second
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        Vector2 mouse = new Vector2(mouseX, 842 - mouseY);
        // model.mousePos();
        moveRats();
        attackRat();
        // attackQueueForEachCat();
        rotater();
        updateProjectiles(deltaTime);

        //unfreezeRats();

        spawnTimer += 0.05;
        if (spawnTimer > ratSpawnDelay && getRatsSpawned() < getRatLimitPerLevel()) {
            spawnRats();
            spawnTimer = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            spawnRats();
        }

        if (Gdx.input.isTouched() && !catMenu.getMenuRectangle().contains(mouse)) { // check for mouse click not in catMenu
            newCat(mouseX, 842 - mouseY);

        }
        else if (Gdx.input.isTouched()){
            catMenu.selector(mouse);
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
    /**
     * Rotates the cats to face the rats they are attacking
     */
    public void rotater(){
        HashMap<Cat, LinkedList<Rat>> attackMap = attackQueueForEachCat();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (!attackableRats.isEmpty()) { 
                Rat firstRat = attackableRats.getFirst();
                cat.rotateImage(firstRat);
            }
        }
    }

    /**
     * Returns the amount of lives the player has left
     * @return lives
     */
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

    /**
     * Returns a hashmap with cats as keys and a linkedlist of rats as values
     * @return
     */

    public HashMap<Cat, LinkedList<Rat>> attackQueueForEachCat() {
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
        HashMap<Cat, LinkedList<Rat>> attackMap = attackQueueForEachCat();
        for (Cat cat : cats) {
            cat.updateAttackTimer(Gdx.graphics.getDeltaTime());
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (cat.canAttack() && !attackableRats.isEmpty()) {
                projectiles.addAll(cat.attack(attackableRats));
                cat.resetAttackTimer();
                
                if (attackableRats.getFirst().isKilled()) {
                    money += 1000;
                    points += 100;
                }
            }
        }
    }

    
    private void updateProjectiles(float dt) {
        HashMap<Cat, LinkedList<Rat>> attackMap = attackQueueForEachCat();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (!attackableRats.isEmpty()) {
                for (Projectile projectile : projectiles) {
                    projectile.update(dt,attackableRats.getFirst(),cat);
                    projectile.pointImageAtRat(attackableRats.getFirst(),cat);
                }
            }
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
    
    
    private void unfreezeRats() {
        for (Rat rat : aliveRats) {
            if (rat.isFrozen()) {
                rat.unfreeze();
            }
        }
    }

    public void transaction() {
      
    }

    public void newCat(int mouseX, int mouseY) {
        // Cat cat = new ShotgunCat();
        // Cat cat = new BasicCat();
        Cat cat = new FreezeCat();
        cat.setPos(mouseX, mouseY);
        addCat(cat);
    }

    public CatMenu getBuyMenu() {
        return catMenu;
    }

}
