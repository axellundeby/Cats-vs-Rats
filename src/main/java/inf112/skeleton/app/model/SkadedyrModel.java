package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.main.SkadedyrMain;
import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.model.entities.rat.RatFactory;
import java.util.List;

public class SkadedyrModel implements ISkadedyrModel {
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Rat> aliveRats = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private RatFactory ratFactory = new RatFactory();
    private int lives = 5;
    private int money = 1000;
    private int points = 0;
    private int level = 0;
    private int ratsSpawned;
    private boolean isPaused = true;
    private float intervalSeconds = (float) 0.05;
    private CatMenu catMenu;
    private float roundOverDelay = 0f;
    private final float DELAY_DURATION = 1f; 
    private final float COIN_DURATION = 1f; 
    private boolean roundOver = false;
    private boolean writeText = false;
    private boolean speedUp = false;
    
    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.catMenu = new CatMenu();
        this.aliveRats = new ArrayList<>();
    }

    public void clockTick() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        updateCatAnimations(deltaTime);
        handleUserInput();
        moveRats();
        attackRat();
        catRotater();
        // updateProjectiles(deltaTime);
        List<Rat> newRats = ratFactory.updateRatFactory(deltaTime, level);
        for (Rat newRat : newRats) {
           // newRat.addTime();//rat.updatePositionAlongPath(deltaTime);
            newRat.moveAlongPath(deltaTime);
            newRat.rotateImage();
            if (!aliveRats.contains(newRat)) {
                aliveRats.add(newRat);
            }
        }
        roundHandler(deltaTime);
        removeDeadOrExitedRats(deltaTime);
    }

    public void removeDeadOrExitedRats(float deltaTime) {
        Iterator<Rat> iterator = aliveRats.iterator();
        while (iterator.hasNext()) {
            Rat rat = iterator.next();
            if (rat.isKilled()) {
                rat.updateCoinVisibility(deltaTime); 
                if (!rat.isrewardClaimed()) {
                    money += rat.getBounty();
                    points += rat.getPoints();
                    rat.rewardClaimed();
                    rat.killedAnimation();
                }
                if (rat.coinVisibleTime >= COIN_DURATION) {
                    iterator.remove();
                }
            } else if (rat.getDirection() == Direction.OUT) {
                if (!rat.isrewardClaimed()) {
                    if (!rat.isExited()) {
                        lives = Math.max(0, lives - 1);
                        rat.exit();
                    }
                    iterator.remove();
                }
            }
        }
    }
    
    private void roundHandler(float deltaTime){
        isRoundOver();
        if(roundOver){
            roundOverDelay += deltaTime;
            if(roundOverDelay >= DELAY_DURATION){
                roundOver(deltaTime);
                roundOverDelay = 0f; 
            }
        } else {
           roundOverDelay = 0f;
           writeText = false;
       }
    }

    private void roundOver(float deltaTime) {
        level++;
        ratFactory.updateRatFactory(deltaTime, level);
        writeText = true;
        nextWaveText();
        setPause();
        for (Cat cat : cats) {
            cat.resetAttackTimer();
        }
    }
    
    private void isRoundOver() {
        int killedRats = 0;
        for (Rat rat : aliveRats) {
            if (rat.isKilled() || rat.isOut()) {
                killedRats++;
            }
            if (killedRats == ratFactory.calculateRatsForRound(level)) {
                 roundOver = true;
                 break;
            }
            roundOver = false;
        }

    }

    public String nextWaveText() {
        if (writeText) {
            return "Round over. Press unPause to continue.";
        }
        return "";
    }

    private void updateCatAnimations(float deltaTime) {
        for (Cat cat : cats) {
            cat.updateAnimation(deltaTime);
        }
    }

    private void handleUserInput() {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        Vector2 mouse = new Vector2(mouseX, 842 - mouseY);

        if (Gdx.input.isTouched() && mouseX < 832) {
            newCat(mouseX, 842 - mouseY);
        }
        if (Gdx.input.isTouched()) {
            catMenu.selector(mouse);
        }
    }

    //fjerne
    @Override
    public void moveRats() {
    }

    @Override
    public void addCat(Cat cat) {
        cats.add(cat);
    }

    @Override
    public void setPause() {
        isPaused = !isPaused;
    }

    @Override
    public boolean isPaused() {
        return isPaused;
    }
    @Override
    public float getSpeed() {
        return intervalSeconds;
    }


    public boolean isSpeedUp() {
        return speedUp;
    }

    @Override
    public void setSpeed() {
        if (!isPaused) {
            speedUp = !speedUp;

            if (intervalSeconds == (float) 0.05) {
                intervalSeconds = (float) 0.0025;
                

            } else {
                intervalSeconds = (float) 0.05;

            }
        }
    }

    @Override
    public void restart() {
        if (isPaused) {
            SkadedyrMain.main(null);
        }
    }

    @Override
    public void exit() {
        if (isPaused) {
            System.exit(0);
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

    @Override
    public int getRatsSpawned() {
        return ratsSpawned;
    }

    @Override
    public int getMoney() {
        return money;
    }
    
    @Override
    public void setMoney(int money) {
        this.money = money;
       
       
    }
   
    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getPoints() {
        return points;
    }


    private void catRotater() {
        HashMap<Cat, LinkedList<Rat>> attackMap = attackQueueForEachCat();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (!attackableRats.isEmpty()) {
                Rat firstRat = attackableRats.getFirst();
                cat.setRotationToward(firstRat);
            }
            cat.rotateImage();
        }
    }

    /**
     * Returns the amount of lives the player has left
     * 
     * @return lives
     */

    @Override
    public int getLives() {
        if (lives <= 0) {
            // gameOver();
        }
        return lives;
    }

    
    public HashMap<Cat, LinkedList<Rat>> attackQueueForEachCat() {
        HashMap<Cat, LinkedList<Rat>> attackMap = new HashMap<>();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = new LinkedList<>();
            for (Rat rat : aliveRats) {
                if (!rat.isKilled() && cat.withinRange(rat)) { //gir mening
                    attackableRats.addLast(rat);
                }
            }
            attackMap.put(cat, attackableRats);
        }
        return attackMap;
    }

    private void attackRat() {
        HashMap<Cat, LinkedList<Rat>> attackMap = attackQueueForEachCat();
        for (Cat cat : cats) {
            cat.updateAttackTimer(Gdx.graphics.getDeltaTime());
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (cat.canAttack() && !attackableRats.isEmpty()) {
                projectiles.addAll(cat.attack(attackableRats));
                cat.resetAttackTimer();
            }
        }
    }

    private void updateProjectiles(float dt) {
        HashMap<Cat, LinkedList<Rat>> attackMap = attackQueueForEachCat();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (!attackableRats.isEmpty()) {
                for (Projectile projectile : projectiles) {
                    projectile.update(dt, attackableRats.getFirst(), cat);
                    projectile.pointImageAtRat(attackableRats.getFirst(), cat);
                }
            }
        }
    }

    @Override
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


    private void newCat(int mouseX, int mouseY) {
        System.out.println(mouseX + " " + mouseY);
        Cat cat = catMenu.getSelectedCat();
        int cost = 0;
        if (cat instanceof BasicCat) {
            cat = new BasicCat();
            cost = cat.getCost();
        } else if (cat instanceof ShotgunCat) {
            cat = new ShotgunCat();
            cost = cat.getCost();
        } else if (cat instanceof FreezeCat) {
            cat = new FreezeCat();
            cost = cat.getCost();
        }
        if (money >= cost) { 
            cat.setPos(mouseX, mouseY);
            addCat(cat);
            money -= cost;
        }
    }

    @Override
    public CatMenu getBuyMenu() {
        return catMenu;
    }
}
