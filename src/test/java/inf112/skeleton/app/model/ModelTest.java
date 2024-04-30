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
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.Cat.PictureSwapper;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;
import inf112.skeleton.app.model.entities.rat.RatFactory;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;


import com.badlogic.gdx.audio.Sound;


public class ModelTest {
    Texture mockTexture;
    private static SkadedyrModel model;
    private BasicCat basicCat;
    private ShotgunCat shotgunCat;
    private TimeSource mockTimeSource;
    private LinkedList<Rat> rats;
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
            Rat rat = new Rat(1000, 10, mockTexture, 50, 20, mockTexture, 25, mockTexture);
            rats.add(rat);
            model.addRat(rat);
        }
    }

    private void addRatsWithLowHp(int amount) {
        for (int i = 0; i < amount; i++) {
            Rat rat = new Rat(10, 10, mockTexture, 50, 20, mockTexture, 25, mockTexture);
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

        Rat rat1 = rats.get(0);
        Rat rat2 = rats.get(1);

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

        Rat rat1 = rats.get(0);
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

            Rat rat = rats.get(0);
            rat.setPosition(new Vector2(15,15));
            
            model.clockTick();

            assertTrue(rat.isKilled());
            assertEquals(rat.getPoints() + initialPoints, model.getPoints());
            assertEquals(rat.getBounty() + initialMoney, model.getMoney());
            assertTrue(rat.isrewardClaimed());
            verify(mockSound, times(2)).play(0.6f);

            for (int index = 0; index < 50; index++) {
                model.clockTick();
            }
            assertTrue(model.getRats().isEmpty());
            rats.removeAll(rats);
    }

    @Test
    void removeExitedRatsTest(){
        addRatsWithLowHp(6);
        model.setPause();
        for (Rat rat : rats) {
            rat.setDirection(Direction.OUT);
        }
        model.clockTick();
        
        assertEquals(0, model.getLives());
        verify(mockSound, times(6)).play(0.6f);
        for (Rat rat : rats) {
            assertTrue(rat.isExited());
        }
        assertTrue(model.getRats().isEmpty());
        rats.removeAll(rats);
    }

    @Test
    void ratHanderTest(){

    }
    
    @Test
    void roundTester(){
        addRatsWithLowHp(1);
        
        assertEquals(0, model.getLevel());
        model.setPause();
        
        
        int killedRatsForRound0 = 0;
        for (Rat rat : rats) {
            rat.setDirection(Direction.OUT);
            killedRatsForRound0++;
        }
        assertEquals(killedRatsForRound0, ratFactory.calculateRatsForRound(model.getLevel()));
        for (Rat rat : rats) {
            assertTrue(rat.isOut());
        }
        
        assertEquals("", model.nextWaveText());
        
        for (int i = 0; i < 100; i++) {
            model.clockTick();
        }
        assertEquals(4, model.getLives());
        assertEquals(1, model.getLevel());
        assertTrue(model.isPaused());
        assertEquals("Round over. Press unPause to continue.", model.nextWaveText());

        rats.removeAll(rats);

        addRatsWithLowHp(6);
        model.setPause();
        model.clockTick();
        int killedRatsForRound1 = 0;
        for (Rat rat : rats) {
            rat.setDirection(Direction.OUT);
            killedRatsForRound1++;
        }
        assertEquals(killedRatsForRound1, ratFactory.calculateRatsForRound(model.getLevel()));
        for (Rat rat : rats) {
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

 
   
}
