package inf112.skeleton.app.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.badlogic.gdx.audio.Sound;
import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.view.States.PlayState;
import inf112.skeleton.app.view.States.State;
import inf112.skeleton.app.model.entities.rat.RatFactory;
import java.util.List;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;

public class SkadedyrModel implements ISkadedyrModel {
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Rat> aliveRats = new ArrayList<>();
    private RatFactory ratFactory;
    private int lives;
    private int money;
    private int points = 0;
    private int level = 0;
    private boolean isPaused;
    private float intervalSeconds = (float) 0.05;
    private CatMenu catMenu;
    private float roundOverDelay = 0f;
    private final float DELAY_DURATION = 1f;
    private final float COIN_DURATION = 1f;
    private boolean roundOver = false;
    private boolean writeText = false;
    private boolean speedUp = false;
    private Cat selectedCat;
    private State currentState;
    private boolean isHelp = false;
    private boolean startGame = false;
    private List<Rat> newRats;
    private GameResourceFactory resourceFactory;
    private TimeSource timeSource;
    private float freezeTimer = 0;
    private static final float RAT_FREEZE_DELAY = 100;


    public SkadedyrModel(GameResourceFactory resourceFactory, TimeSource timeSource) {
        this.resourceFactory = resourceFactory;
        this.timeSource = timeSource;
        catMenu = new CatMenu(resourceFactory);
        ratFactory = new RatFactory(resourceFactory);
        cats = new ArrayList<>();
        aliveRats = new ArrayList<>();
        newRats = new ArrayList<>(); 
        initializeGame();
    }

    private void initializeGame() {
        cats.clear();
        aliveRats.clear();
        newRats.clear();
        ratFactory.resetRatFactory();
        money = 10000;
        lives = 5;
        isPaused = true;
        intervalSeconds = 0.05f;
        speedUp = false;
    }

    private void removeAllRats() {
        aliveRats = new ArrayList<>();
        newRats = new ArrayList<>();
    }

    public void initCatMenu() {
        catMenu.init();
    }

    public void setState(State newState) {
        this.currentState = newState;
    }

    public State getState() {
        return currentState;
    }

    public void clockTick() {
        if (!isPaused) {
            float deltaTime = timeSource.getDeltaTime();
            updateCatAnimations(deltaTime);
            attackRat();
            catRotater();
            ratHandler(deltaTime);
            roundHandler(deltaTime);
            removeDeadOrExitedRats(deltaTime);
            freezeHandler(deltaTime);
        }
    }

    private void freezeHandler(float deltaTime) {
       for (Rat rat : aliveRats) {
            if (rat.isFrozen()) {
                rat.freeze();
                freezeTimer += deltaTime;
                if (freezeTimer > RAT_FREEZE_DELAY) {
                    rat.unfreeze();
                    freezeTimer = 0;
                }
            }
       }
    }

    private void ratHandler(float deltaTime){
        newRats = ratFactory.updateRatFactory(deltaTime, level); 
            for (Rat newRat : newRats) {
                newRat.moveAlongPath(deltaTime);
                newRat.rotateImage();
                if (!aliveRats.contains(newRat)) {
                    aliveRats.add(newRat);
                }
            }
    }

    private void removeDeadOrExitedRats(float deltaTime) {
        Iterator<Rat> iterator = aliveRats.iterator();
        while (iterator.hasNext()) {
            Rat rat = iterator.next();
            if (rat.isKilled()) {
                rat.updateCoinVisibility(deltaTime);
                if (!rat.isrewardClaimed()) {
                    // Buttons update each time a rat is killed
                    if (currentState instanceof PlayState) {
                     //   ((PlayState) currentState).updateUpgradeButtons();
                    }
                    money += rat.getBounty();
                    points += rat.getPoints();
                    rat.rewardClaimed();
                    Sound collectCoinSound = resourceFactory.getSound("sound/coin.mp3");
                    collectCoinSound.play(0.6f);
                }
                if (rat.getCoinVisibleTime() >= COIN_DURATION) {
                    iterator.remove();
                }
            } else if (rat.getDirection() == Direction.OUT) {
                if (!rat.isExited()) {
                    lives = Math.max(0, lives - 1);
                    Sound livesSound = resourceFactory.getSound("sound/hp.mp3");
                    livesSound.play(0.6f);
                    rat.exit();
                    iterator.remove();
                }
            }
        }
    }

    private void roundHandler(float deltaTime) {
        isRoundOver();
        if (roundOver) {
            roundOverDelay += deltaTime;
            if (roundOverDelay >= DELAY_DURATION) {
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
        writeText = true;
        nextWaveText();
        removeAllRats();

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
        } else if (level == 0 && isPaused) {
            return "Press unPause to start";
        }
        return "";
    }

    public boolean pressedUppgradeButton() {
        return true;
    }

    public boolean triendToAddCat() {
        return true;
    }

    public String uppgradeErrorText() {
        if (getSelectedCat() == null && pressedUppgradeButton()) {
            return "No cat selected";
        }
        return "";
    }

    public String setErrorText() {
        if (getSelectedCat() == null && triendToAddCat()) {
            return "No enough money to buy cat";
        }
        return "";
    }

    private void updateCatAnimations(float deltaTime) {
        for (Cat cat : cats) {
            cat.updateAnimation(deltaTime);
        }
    }

    public Cat getSelectedCat() {
        return selectedCat;
    }

    public Cat setSelectedCat(Cat cat) {
        return selectedCat = cat;
    }

    
    public void addCat(Cat cat) {
        cats.add(cat);
    }

    @Override
    public void setPause() {
        isPaused = !isPaused;
        if (currentState instanceof PlayState) {
            ((PlayState) currentState).updateMenuButtons();
        }
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
        speedUp = !speedUp;
        if (currentState instanceof PlayState) {
            ((PlayState) currentState).updateMenuButtons();
        }
        if (speedUp) {
            intervalSeconds = (float) 0.0025;
            

        } else {
            intervalSeconds = (float) 0.05;
        }
    }

    @Override
    public String exit() {
        if (isPaused) {
            System.exit(0);
        }
        return "cannot exit while game is running";
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
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
        if (currentState instanceof PlayState) {
            ((PlayState) currentState).updateUpgradeButtons();
            ((PlayState) currentState).addUpgradeButtonsToStage();
        }
        Sound buySound = resourceFactory.getSound("sound/cashier.mp3");
        buySound.play(0.6f);

    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public boolean isGameWon() {
        return level == 10;
    }

    public void setLevel(int levelSetter){
        level = levelSetter;
    }

    public void setHelp() {
        isHelp = !isHelp;
        startGame = false;
    }

    public boolean getHelp() {
        return isHelp;
    }

    public void setStartGame() {
        startGame = !startGame;
        isHelp = false;
    }

    public boolean getStartGame() {
        return startGame;
    }

    @Override
    public boolean isGameOver() {
        return lives <= 0;
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
        }
    }

    /**
     * Returns the amount of lives the player has left
     * 
     * @return lives
     */

    @Override
    public int getLives() {
        return lives;
    }

    public void restart() {
        initializeGame();

    }

    public void addRat(Rat rat) {
        aliveRats.add(rat);
    }

    public ArrayList<Rat> getAliveRats() {
        return aliveRats;
    }

   
    private HashMap<Cat, LinkedList<Rat>> attackQueueForEachCat() {
        HashMap<Cat, LinkedList<Rat>> attackMap = new HashMap<>();
        for (Cat cat : cats) {
            LinkedList<Rat> attackableRats = new LinkedList<>();
            for (Rat rat : aliveRats) {
                if (!rat.isKilled() && cat.withinRange(rat)) { 
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
            cat.updateAttackTimer(timeSource.getDeltaTime());
            LinkedList<Rat> attackableRats = attackMap.get(cat);
            if (cat.canAttack() && !attackableRats.isEmpty()) {
                cat.attack(attackableRats);
                cat.resetAttackTimer();
            }
        }
    }

    @Override
    public CatMenu getCatMenu() {
        return catMenu;
    }


}
