package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.model.entities.rat.RatFactory;
import java.util.List;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;
import inf112.skeleton.app.view.states.PlayState;
import inf112.skeleton.app.view.states.State;

public class SkadedyrModel implements ISkadedyrModel {
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Rat> aliveRats = new ArrayList<>();
    private RatFactory ratFactory;
    private int lives = 5;
    private int money = 1000000;
    private int points = 0;
    private int level = 0;
    private int ratsSpawned;
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
    private boolean uppgradePressed = false;
    private List<Rat> newRats;
    private GameResourceFactory resourceFactory;
    private TimeSource timeSource;
    private float elapsedTime = 0.0f;

   
    public SkadedyrModel(GameResourceFactory resourceFactory, TimeSource timeSource) {
        this.resourceFactory = resourceFactory;
        this.timeSource = timeSource;
        catMenu = new CatMenu(resourceFactory, timeSource);
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
            //handleUserInput();
            attackRat();
            catRotater();
            ratHandler(deltaTime);
            roundHandler(deltaTime);
            removeDeadOrExitedRats(deltaTime);
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
                if (rat.coinVisibleTime >= COIN_DURATION) {
                    iterator.remove();
                }
            } else if (rat.getDirection() == Direction.OUT) {
                if (!rat.isrewardClaimed()) {
                    if (!rat.isExited()) {
                        lives = Math.max(0, lives - 1);
                        Sound livesSound = resourceFactory.getSound("sound/hp.mp3");
                        livesSound.play(0.6f);
                        rat.exit();
                    }
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
        return uppgradePressed = true;
    }

    public String uppgradeErrorText() {
        if (getSelectedCat() == null && pressedUppgradeButton()) {
            return "No cat selected";
        }
        return "";
    }

    private void updateCatAnimations(float deltaTime) {
        for (Cat cat : cats) {
            cat.updateAnimation(deltaTime);
        }
    }

    public void handleUserInput() {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        Vector2 mouse = new Vector2(mouseX, 842 - mouseY);
        if (Gdx.input.isTouched() && mouseY > 100 && mouseY < 650) {
            newCat(mouseX, 842 - mouseY);
            catMenu.deselect();
        }
        if (Gdx.input.isTouched()) {
            catMenu.selector(mouse);
            selectCat(mouse);
        }
    }

    private void selectCat(Vector2 mouse) {
        if (mouse.y < 200)
            return;
        for (Cat cat : cats) {
            if (cat.getSelectionCircle().contains(mouse)) {
                selectedCat = cat;
                return;
            }
        }
        selectedCat = null;
    }

    public Cat getSelectedCat() {
        return selectedCat;
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
    public void exit() {
        if (isPaused) {
            System.exit(0);
        }
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

    private void newCat(float mouseX, float mouseY) {
        Cat cat = catMenu.getSelectedCat();
        int cost = 0;
        if (cat instanceof BasicCat) {
            cat = new BasicCat(resourceFactory);

        } else if (cat instanceof ShotgunCat) {
            cat = new ShotgunCat(resourceFactory);

        } else if (cat instanceof FreezeCat) {
            cat = new FreezeCat(resourceFactory, timeSource);
        }
        if (cat != null) {
            cost = cat.getCost();

            if (money >= cost) {
                cat.setPos(mouseX, mouseY);
                addCat(cat);
                setMoney(money - cost);
            }
        }
    }

    @Override
    public CatMenu getBuyMenu() {
        return catMenu;
    }

}
