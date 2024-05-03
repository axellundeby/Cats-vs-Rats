package inf112.skeleton.app.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.badlogic.gdx.audio.Sound;
import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.cat.ICat;
import inf112.skeleton.app.model.entities.rat.IRat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.model.entities.rat.RatFactory;
import java.util.List;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;
import inf112.skeleton.app.view.states.PlayState;
import inf112.skeleton.app.view.states.State;

public class SkadedyrModel implements ISkadedyrModel {
    private ArrayList<ICat> cats = new ArrayList<>();
    private ArrayList<IRat> aliveRats = new ArrayList<>();
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
    private ICat selectedCat;
    private State currentState;
    private boolean isHelp = false;
    private boolean startGame = false;
    private List<IRat> newRats;
    private GameResourceFactory resourceFactory;
    private TimeSource timeSource;
    private float freezeTimer = 0;
    private static final float RAT_FREEZE_DELAY = 100;
    private int killedRats = 0;


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

    @Override
    public void initCatMenu() {
        catMenu.init();
    }

    @Override
    public void setState(State newState) {
        this.currentState = newState;
    }

    @Override
    public State getState() {
        return currentState;
    }


    @Override
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
       for (IRat rat : aliveRats) {
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
            for (IRat newRat : newRats) {
                newRat.moveAlongPath(deltaTime);
                if (!aliveRats.contains(newRat)) {
                    aliveRats.add(newRat);
                }
            }
    }

    private void removeDeadOrExitedRats(float deltaTime) {
        Iterator<IRat> iterator = aliveRats.iterator();
        while (iterator.hasNext()) {
            IRat rat = iterator.next();
            if (rat.isKilled()) {
                rat.updateCoinVisibility(deltaTime);
                if (!rat.isrewardClaimed()) {
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
        ratFactory.resetRatFactory();
        killedRats = 0;
        setPause();
        roundOver = false;
        for (ICat cat : cats) {
            cat.resetAttackTimer();
        }
    }

    private void isRoundOver() {
        int totalRatsForRound = ratFactory.calculateRatsForRound(level);
        if (!roundOver) {
            for (IRat rat : aliveRats) {
                if ((rat.isKilled() || rat.isOut()) && !rat.isCounted()) {
                    killedRats++;
                    rat.setCounted(true); 
                }
            }
            roundOver = (killedRats >= totalRatsForRound);
        }
    }
    

    @Override
    public String nextWaveText() {
        if (writeText) {
            return "Round over. Press unPause to continue.";
        } else if (level == 0 && isPaused) {
            return "Press unPause to start";
        }
        return "";
    }

    @Override
    public boolean pressedUppgradeButton() {
        return true;
    }

    @Override
    public boolean triendToAddCat() {
        return true;
    }

    @Override
    public String uppgradeErrorText() {
        if (getSelectedCat() == null && pressedUppgradeButton()) {
            return "No cat selected";
        }
        return "";
    }

    @Override
    public String setErrorText() {
        if (getSelectedCat() == null && triendToAddCat()) {
            return "No enough money to buy cat";
        }
        return "";
    }

    private void updateCatAnimations(float deltaTime) {
        for (ICat cat : cats) {
            cat.updateAnimation(deltaTime);
        }
    }

    @Override
    public ICat getSelectedCat() {
        return selectedCat;
    }

    @Override
    public ICat setSelectedCat(ICat cat) {
        return selectedCat = cat;
    }

    @Override
    public void addCat(ICat cat) {
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

    @Override
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
    public ArrayList<ICat> getCats() {
        return cats;
    }

    @Override
    public ArrayList<IRat> getRats() {
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

    @Override
    public void setLevel(int levelSetter){
        level = levelSetter;
    }

    @Override
    public void setHelp() {
        isHelp = !isHelp;
        startGame = false;
    }

    @Override
    public boolean getHelp() {
        return isHelp;
    }

    @Override
    public void setStartGame() {
        startGame = !startGame;
        isHelp = false;
    }

    @Override
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
        HashMap<ICat, LinkedList<IRat>> attackMap = attackQueueForEachCat();
        for (ICat cat : cats) {
            LinkedList<IRat> attackableRats = attackMap.get(cat);
            if (!attackableRats.isEmpty()) {
                IRat firstRat = attackableRats.getFirst();
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

    @Override
    public void restart() {
        initializeGame();

    }

    @Override
    public void addRat(IRat rat) {
        aliveRats.add(rat);
    }

    @Override
    public ArrayList<IRat> getAliveRats() {
        return aliveRats;
    }

   
    private HashMap<ICat, LinkedList<IRat>> attackQueueForEachCat() {
        HashMap<ICat, LinkedList<IRat>> attackMap = new HashMap<>();
        for (ICat cat : cats) {
            LinkedList<IRat> attackableRats = new LinkedList<>();
            for (IRat rat : aliveRats) {
                if (!rat.isKilled() && cat.withinRange(rat)) { 
                    attackableRats.addLast(rat);
                }
            }
            attackMap.put(cat, attackableRats);
        }
        return attackMap;
    }

  
    private void attackRat() {
        HashMap<ICat, LinkedList<IRat>> attackMap = attackQueueForEachCat();
        for (ICat cat : cats) {
            cat.updateAttackTimer(timeSource.getDeltaTime());
            LinkedList<IRat> attackableRats = attackMap.get(cat);
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
