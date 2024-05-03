package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.LinkedList;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.Cat.PictureSwapper;
import inf112.skeleton.app.model.entities.rat.IRat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.model.entities.rat.RatFactory;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;
import com.badlogic.gdx.audio.Sound;



public class ModelTest {
    @Mock
    private Texture mockTexture;

    @Mock
    private Texture aliveTextureMock;

    @Mock
    private Texture frozenTextureMock;

    @Mock
    private Texture deadTextureMock;

    private static SkadedyrModel model;
    private BasicCat basicCat;
    private ShotgunCat shotgunCat;
    private FreezeCat freezeCat;
    private TimeSource mockTimeSource;
    private LinkedList<IRat> rats;
    private RatFactory ratFactory;


    @Mock
    private Sound mockSound; 

   
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  
        GameResourceFactory mockFactory = mock(GameResourceFactory.class);
        mockTexture = mock(Texture.class);
        
        
        mockTimeSource = mock(TimeSource.class);
        when(mockTimeSource.getDeltaTime()).thenReturn(0.1f);
        when(mockFactory.getTexture(anyString())).thenReturn(mockTexture);
        when(mockFactory.getSound(anyString())).thenReturn(mockSound); 
    
        shotgunCat = new ShotgunCat(mockFactory);
        basicCat = new BasicCat(mockFactory);
        freezeCat = new FreezeCat(mockFactory);
        model = new SkadedyrModel(mockFactory, mockTimeSource);
        ratFactory = new RatFactory(mockFactory);
        rats = new LinkedList<>();
    }

   
    
    @Test
    void updateCatAnimationsTest(){
        addRatsWithHighHp(3);
        model.addCat(basicCat);
        basicCat.setPos(500, 500);

        model.setPause();

        PictureSwapper catImage = basicCat.getCurrentState();
        assertEquals(catImage, PictureSwapper.DEFAULT);
        basicCat.attack(rats);
        catImage = basicCat.getCurrentState(); 
        assertEquals(catImage, PictureSwapper.ATTACK);

        for (int i = 0; i < 100; i++) {
            model.clockTick();
        }
        catImage = basicCat.getCurrentState(); 
        assertEquals(catImage, PictureSwapper.DEFAULT);
        rats.removeAll(rats);
    }

    private void addRatsWithHighHp(int amount) {
        for (int i = 0; i < amount; i++) {
            Rat rat = new Rat(1000, 15, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock);
            rats.add(rat);
            model.addRat(rat);
        }
    }

    private void addRatsWithLowHp(int amount) {
        for (int i = 0; i < amount; i++) {
            Rat rat = new Rat(10, 10, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock);
            rats.add(rat);
            model.addRat(rat);
        }
    }

    @Test
    void attackRatTest1() {
        addRatsWithHighHp(3);
        model.setPause();
        
        model.addCat(basicCat);
        basicCat.setPos(15, 15);
        model.addCat(shotgunCat);
        shotgunCat.setPos(800, 800);

        IRat rat1 = rats.get(0);
        IRat rat2 = rats.get(1);

        int initialHealth1 = rat1.getHealth();
        int initialHealth2 = rat2.getHealth();
        rat1.setPosition(new Vector2(15,15));
        rat2.setPosition(new Vector2(10,10));
        model.clockTick();
        int expectedHealth1 = initialHealth1 - basicCat.getStrength();

        assertEquals(basicCat.getFireRate(), basicCat.getAttackTimer());
        assertEquals(0, shotgunCat.getAttackTimer());
        assertEquals(expectedHealth1, rat1.getHealth());
        assertEquals(initialHealth2, rat2.getHealth());
        rats.removeAll(rats);
    }

    @Test
    void attackRatTest2() {
        addRatsWithLowHp(3);
        model.setPause();
        
        model.addCat(basicCat);
        basicCat.setPos(15, 15);
        model.addCat(shotgunCat);
        shotgunCat.setPos(800, 800);

        IRat rat1 = rats.get(0);
        rat1.setPosition(new Vector2(15,15));
        model.clockTick();
    
        assertEquals(basicCat.getFireRate(), basicCat.getAttackTimer());
        assertEquals(0, shotgunCat.getAttackTimer());
        assertTrue(rat1.isKilled());
        rats.removeAll(rats);
    }

    @Test
    void removeKilledRatsTest(){
        addRatsWithLowHp(1);
        model.setPause();

        basicCat.setPos(15, 15);
        model.addCat(basicCat);
        int initialPoints = model.getPoints();
        int initialMoney = model.getMoney();

        IRat rat = rats.get(0);
        rat.setPosition(new Vector2(15,15));
        
        model.clockTick();
        assertTrue(rat.isKilled());
        assertEquals(rat.getPoints() + initialPoints, model.getPoints());
        assertEquals(rat.getBounty() + initialMoney, model.getMoney());
        assertTrue(rat.isrewardClaimed());
        verify(mockSound, times(2)).play(0.6f);
        rats.removeAll(rats);
    }

    @Test
    void removeExitedRatsTest(){
        addRatsWithLowHp(6);
        model.setPause();
        for (IRat rat : rats) {
            rat.setDirection(Direction.OUT);
        }
        model.clockTick();
        
        assertEquals(0, model.getLives());
        assertTrue(model.isGameOver());
        verify(mockSound, times(6)).play(0.6f);
        for (IRat rat : rats) {
            assertTrue(rat.isExited());
        }
        assertTrue(model.getRats().isEmpty());
        rats.removeAll(rats);
    }
    @Test
    void ratHandlerTest() {
        addRatsWithHighHp(5);
        model.setLevel(1);
        model.setPause();

        for (int i = 0; i < 15; i++) {
            model.clockTick();
        }
        assertEquals(rats.size(), ratFactory.calculateRatsForRound(model.getLevel()));
    }
    
    @Test
    void roundTester(){
        addRatsWithLowHp(2);
        
        assertEquals(0, model.getLevel());
        model.setPause();
        
        
        int killedRatsForRound0 = 0;
        for (IRat rat : rats) {
            rat.setDirection(Direction.OUT);
            killedRatsForRound0++;
        }
        assertEquals(killedRatsForRound0, ratFactory.calculateRatsForRound(model.getLevel()));
        for (IRat rat : rats) {
            assertTrue(rat.isOut());
        }
        
        assertEquals("", model.nextWaveText());
        
        for (int i = 0; i < 100; i++) {
            model.clockTick();
        }
        assertEquals(3, model.getLives());
        assertEquals(1, model.getLevel());
        assertTrue(model.isPaused());
        assertEquals("Round over. Press unPause to continue.", model.nextWaveText());

        rats.removeAll(rats);

        addRatsWithLowHp(5);
        model.setPause();
        model.clockTick();
        int killedRatsForRound1 = 0;
        for (IRat rat : rats) {
            rat.setDirection(Direction.OUT);
            killedRatsForRound1++;
        }
        assertEquals(killedRatsForRound1, ratFactory.calculateRatsForRound(model.getLevel()));
        for (IRat rat : rats) {
            assertTrue(rat.isOut());
        }
        assertEquals("", model.nextWaveText());
        for (int i = 0; i < 100; i++) {
            model.clockTick();
        }
        assertEquals(0, model.getLives());
        assertEquals(2, model.getLevel());
        assertTrue(model.isPaused());
        assertEquals("Round over. Press unPause to continue.", model.nextWaveText());
        rats.removeAll(rats);
    }

  
    private float calculateExpectedAngle(float catX, float catY, float targetX, float targetY) {
        float dx = targetX - catX;
        float dy = targetY - catY;
        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
        return angle - 90;
    }
    

    @Test
    void rotateTester(){
        addRatsWithHighHp(3);

        model.setPause();
        model.addCat(basicCat);
        basicCat.setPos(15, 15);
        model.addCat(shotgunCat);
        shotgunCat.setPos(800, 800);

        for (int i = 0; i < 5; i++) {
            rats.get(0).setPosition(new Vector2(5 + (i * 20) , 30 + (i * -5)));       
            rats.get(1).setPosition(new Vector2(800 + (i * -5),800 + (i * 10)));       
            model.clockTick();

            assertTrue(basicCat.withinRange(rats.get(0)));
            assertFalse(shotgunCat.withinRange(rats.get(0)));
            assertTrue(shotgunCat.withinRange(rats.get(1)));
            
            float expectedAngleForFirstRat = calculateExpectedAngle(basicCat.getPosition().x, basicCat.getPosition().y, rats.get(0).getPosition().x, rats.get(0).getPosition().y);
            float expectedAngleForSecondRat = calculateExpectedAngle(shotgunCat.getPosition().x, shotgunCat.getPosition().y, rats.get(1).getPosition().x, rats.get(1).getPosition().y);
            
            assertEquals(expectedAngleForFirstRat, basicCat.getRotationAngle());
            assertEquals(expectedAngleForSecondRat, shotgunCat.getRotationAngle());
        }
        rats.removeAll(rats);
    }

    
    @Test
    void pauseToggleTest() {
        assertTrue(model.isPaused());
        model.setPause();
        assertFalse(model.isPaused());
    }

   
    @Test
    void clockTickTest() {
        model.clockTick();
        assertTrue(model.isPaused());
    }

    @Test
    void testPauseToggle() {
        boolean initialPauseState = model.isPaused();
        model.setPause();
        assertNotEquals(initialPauseState, model.isPaused());
    }

    @Test
    void testSpeedToggle() {
        float initialSpeed = model.getSpeed();
        model.setSpeed();
        assertNotEquals(initialSpeed, model.getSpeed());
        model.setSpeed();
        assertEquals(initialSpeed, model.getSpeed());
    }

    @Test
    void testMoneyManagement() {
        int initialMoney = model.getMoney();
        model.setMoney(initialMoney + 100);
        assertEquals(initialMoney + 100, model.getMoney());
    }


    @Test
    void speedUpTest(){
        float initialSpeed = model.getSpeed();
        model.setSpeed();
        float newSpeed = model.getSpeed();
        assertTrue(initialSpeed > newSpeed);
        assertTrue(model.isSpeedUp());
        model.setSpeed();
        assertTrue(model.getSpeed() > newSpeed);
    }

   @Test
   void freezeTest(){
         addRatsWithHighHp(3);
         model.addCat(freezeCat);
         model.setPause();
         freezeCat.setPos(15, 15);
        
        rats.get(0).setPosition(new Vector2(15,15));       
        model.clockTick();
        assertTrue(freezeCat.withinRange(rats.get(0)));
        assertEquals(frozenTextureMock, rats.get(0).getTexture());
        assertEquals(0, rats.get(0).getEffectiveSpeed());
         

         for (int i = 0; i < 1000; i++) {
            model.clockTick();
         }

        assertEquals(aliveTextureMock, rats.get(0).getTexture());
        assertEquals(15, rats.get(0).getEffectiveSpeed());
        rats.removeAll(rats);
   }
   

    @Test
    void getAliveRatsTest(){
        addRatsWithHighHp(3);
        assertEquals(3, model.getAliveRats().size());
        rats.removeAll(rats);
    }

    @Test 
    void restartGameTest(){
        model.restart();
        assertEquals(5, model.getLives());
        assertEquals(0, model.getLevel());
        assertEquals(10000, model.getMoney());
        assertEquals(0, model.getPoints());
        assertTrue(model.isPaused());
    }

    @Test
    void gameIsWonTest(){
        assertFalse(model.isGameWon());
        model.setLevel(10);
        assertTrue(model.isGameWon());
    }

    @Test
    void setLevelTester(){
        model.setLevel(10);
        assertEquals(10, model.getLevel());
    }

    @Test
    void helpTester(){
        model.setStartGame();
        assertFalse(model.getHelp());
        assertTrue(model.getStartGame());
        model.setHelp();
        assertTrue(model.getHelp());
        assertFalse(model.getStartGame());
    }

    @Test 
    void setMoneyTest(){
        model.setMoney(1000);
        assertEquals(1000, model.getMoney());
        verify(mockSound, times(1)).play(0.6f);

    }

    @Test 
    void getCatsTest(){
        model.addCat(basicCat);
        model.addCat(shotgunCat);
        assertEquals(2, model.getCats().size());
    }

    @Test
    void startTextTest(){
        assertEquals("Press unPause to start", model.nextWaveText());
        model.setPause();
        model.clockTick();
        assertEquals("", model.nextWaveText());
    }

    @Test 
    void catSelectedTextTest(){
        assertEquals("No cat selected", model.uppgradeErrorText());
        model.addCat(basicCat);
        model.setSelectedCat(basicCat);
        assertEquals("", model.uppgradeErrorText());

    }

    @Test
    void getAndSetSelectedCatTest(){
        model.setSelectedCat(basicCat);
        assertEquals(basicCat, model.getSelectedCat());
    }



}

   



 
   

