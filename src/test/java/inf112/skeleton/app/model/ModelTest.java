package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.model.entities.cat.Cat.PictureSwapper;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;

public class ModelTest {
    Texture mockTexture;
    private static SkadedyrModel model;
    private BasicCat basicCat;
    private ShotgunCat shotgunCat;
    private TimeSource mockTimeSource;
    private LinkedList<Rat> rats;
  

   
    @BeforeEach
    public void setup() {
        GameResourceFactory mockFactory = mock(GameResourceFactory.class);
        Texture mockTexture = mock(Texture.class);
        mockTimeSource = mock(TimeSource.class);  // Use the class-level mockTimeSource
        when(mockTimeSource.getDeltaTime()).thenReturn(0.1f);
        when(mockFactory.getTexture(anyString())).thenReturn(mockTexture);

        shotgunCat = new ShotgunCat(mockFactory);
        basicCat = new BasicCat(mockFactory);
        model = new SkadedyrModel(mockFactory, mockTimeSource);
        rats = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            rats.add(new Rat(50, 10, mockTexture, 50, 20, mockTexture, 25, mockTexture));
        }

        for (Rat rat : rats) {
            model.addRat(rat);
        }

    }


    
    @Test
    void updateCatAnimationsTest(){
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
    }

    @Test
    void attackRatTest() {
        model.addCat(basicCat);
        basicCat.setPos(15, 15);
    
        model.addCat(shotgunCat);
        shotgunCat.setPos(800, 800);
        
        for (int i = 0; i < rats.size(); i++) {
            rats.get(i).setPosition(new Vector2(15, 15));
        }
    
        assertTrue(basicCat.withinRange(rats.get(0)));
        assertFalse(shotgunCat.withinRange(rats.get(0)));
        assertFalse(model.attackQueueForEachCat().get(basicCat).isEmpty());

        
        int initialHealth = rats.get(0).getHealth();
        System.out.println("Initial Health: " + initialHealth);
    
        model.clockTick(); 
    
        int finalHealth = rats.get(0).getHealth();
        System.out.println("Final Health: " + finalHealth);
    
        assertTrue(finalHealth < initialHealth, "Rat should have taken damage but did not.");
    }

    @Test
    void removeDeadOrExitedRatsTest(){

    }
    
    @Test
    void roundTester(){

    }

    private float calculateExpectedAngle(float catX, float catY, float targetX, float targetY) {
        float dx = targetX - catX;
        float dy = targetY - catY;
        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
        return angle - 90;
    }
    

    @Test
    void rotateTester(){
        model.setPause();
        model.addCat(basicCat);
        basicCat.setPos(15, 15);
    
        model.addCat(shotgunCat);
        shotgunCat.setPos(800, 800);
        for (int i = 0; i < 5; i++) {
            rats.get(0).setPosition(new Vector2(5 + (i * 2) , 5 + (i * 2)));
            rats.get(1).setPosition(new Vector2(5 + (i * 2) , 5 + (i * 2)));            
            model.clockTick();

            assertTrue(basicCat.withinRange(rats.get(0)));
            assertFalse(shotgunCat.withinRange(rats.get(0)));
            assertFalse(model.getAliveRats().isEmpty());
            
            float expectedAngle = calculateExpectedAngle(basicCat.getPosition().x, basicCat.getPosition().y, rats.get(0).getPosition().x, rats.get(0).getPosition().y);
            System.out.println("Expected Angle: " + expectedAngle);
            System.out.println("Cat Rotation: " + basicCat.getRotationAngle());
            System.out.println(model.attackQueueForEachCat().get(basicCat).size());
            assertEquals(expectedAngle, basicCat.getRotationAngle());
        }
    }

    @Test 
    void ratsMoveTest(){}


    
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
