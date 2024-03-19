package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.model.entities.rat.RatFactory;

public class SkadedyrModel implements ISkadedyrModel {
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Rat> aliveRats = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private RatFactory ratFactory = new RatFactory();
    private int lives = 5;
    private int money = 3000;
    private int points = 0;
    private int level = 0;
    private int ratsSpawned;
    private Rat testRat;
    private float spawnTimer = 0;
    private boolean isPaused = false;
    private static final int RAT_SPAWN_DELAY = 5; 


    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.aliveRats = new ArrayList<>();
    }

    public void clockTick() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        updateCatAnimations(deltaTime);
        handleUserInput();
        moveRats(); 
        attackRat();
        rotater();
        updateProjectiles(deltaTime);
        spawnRatsWithDelay();
    }
    

    private void updateCatAnimations(float deltaTime) {
        for (Cat cat : cats) {
            cat.updateAnimation(deltaTime);
        }
    }

    private void handleUserInput() {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        
        if (Gdx.input.isTouched()) {
            newCat(mouseX, 842 - mouseY);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            spawnRats();
        }
    }

    private void spawnRatsWithDelay() {
        spawnTimer += 0.05;
        if (spawnTimer > RAT_SPAWN_DELAY && ratsSpawned < ratFactory.calculateBalloonsForRound(1)) {
            spawnRats();
            spawnTimer = 0;
        }
    }

    public void spawnRats() {
        this.testRat = new BasicRat();
        addRat(testRat);
        ratsSpawned++;
    }

    public void nextWave() {
        level++;
        ratsSpawned = 0;
    }

    public void setPause() {
        isPaused = !isPaused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public String everyRatDead() {
        if (aliveRats.isEmpty() && !isPaused) {
            isPaused = true;
            nextWave();
            return "Round over. Game is paused. Press 'P' to continue.";
        }
        return "";
    }

    @Override
    public void moveRats() {
        for (Rat rat : aliveRats) {
            rat.move();
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


    public void gameOver() {
        Gdx.app.exit(); // jacob skjerm
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
            }
            for (Rat rat : attackableRats) {
                if (rat.isKilled()) {
                    money += rat.getBounty();
                    points += rat.getPoints();
                }
            }
        }
    }


    public void transaction() {
      
    }

    /**
     * Updates the projectiles
     * @param dt
     */
    public void updateProjectiles(float dt) {
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


    public void newCat(int mouseX, int mouseY) {
        Cat gangsta = new ShotgunCat();
        Cat froze = new FreezeCat();
        Cat meow = new BasicCat();
        meow.setPos(mouseX, mouseY);
        addCat(meow);
    }

}
