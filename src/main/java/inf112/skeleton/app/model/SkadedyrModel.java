package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.view.States.GameStateManager;
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
    private boolean isPaused = true;
    private float intervalSeconds = (float) 0.05;
    private CatMenu catMenu;


    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.catMenu = new CatMenu();

    }
    
    public void clockTick() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        updateCatAnimations(deltaTime);
        handleUserInput();
        moveRats(); 
        attackRat();
        rotater();
        updateProjectiles(deltaTime);
        roundOver(deltaTime);
        aliveRats = ratFactory.updateRatFactory(deltaTime,level); 
    }

    //dette må sjekkes for konstant
    private void roundOver(float deltaTime) {
        int killedRats = 0;
        for (Rat rat : aliveRats) {
            if (rat.isKilled() || rat.isOut()) {
                killedRats++;
            }
            if (killedRats == ratFactory.calculateRatsForRound(level)) {
                level++;
                ratFactory.updateRatFactory(deltaTime, level);
                isPaused = true;
                nextWave();
            }
            
        }
    }
    

    private void updateCatAnimations(float deltaTime) {
        for (Cat cat : cats) {
            cat.updateAnimation(deltaTime);
        }
    }

    private void handleUserInput() {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        Vector2 mouse = new Vector2(mouseX, 842-mouseY);
        
        if (Gdx.input.isTouched() && mouseX < 832) {
            newCat(mouseX, 842 - mouseY);
        }
        if (Gdx.input.isTouched()){catMenu.selector(mouse);}
    }
    

    //feil her ja, vil heller ha if round over
    public String nextWave() {
        if (aliveRats.isEmpty() && !isPaused) {
            //isPaused = true;
            //level++;
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

    public void setPause() {
        isPaused = !isPaused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public float getSpeed(){
        System.out.println("Speed: " + intervalSeconds);
       return intervalSeconds;
    }

    public void setSpeed() {
        if (intervalSeconds == (float) 0.05) {
            intervalSeconds = (float) 0.0025;
            System.out.println("Speed up:" + intervalSeconds);
        } else {
            intervalSeconds = (float) 0.05;
            System.out.println("Normal speed:" + intervalSeconds);
        }
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


    // public void gameOver() {
    //     GameStateManager.set(new GameOverState(null));

    //     //Gdx.app.exit(); // jacob skjerm
    // }

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
           // gameOver();
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


    // public void newCat(int mouseX, int mouseY) {
    //     Cat gangsta = new ShotgunCat();
    //     Cat froze = new FreezeCat();
    //     Cat meow = new BasicCat();
    //     meow.setPos(mouseX, mouseY);
    //     addCat(meow);
    // }
    public void newCat(int mouseX, int mouseY) {
        // Cat cat = new ShotgunCat();
        // Cat cat = new BasicCat();
        // Cat cat = new FreezeCat();
        Cat cat = catMenu.getSelectedCat();

        if (cat instanceof BasicCat){
            cat = new BasicCat();
        }
        else if (cat instanceof ShotgunCat){
            cat = new ShotgunCat();
        }
        else if (cat instanceof FreezeCat){
            cat = new FreezeCat();
        }

    
        cat.setPos(mouseX, mouseY);
        addCat(cat);
    }



    public CatMenu getBuyMenu() {
        return catMenu;
    }
}


