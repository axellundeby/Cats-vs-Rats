package inf112.skeleton.app.model.entities.cat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.cat.Cat.PictureSwapper;
import inf112.skeleton.app.model.entities.rat.Rat;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestCat {

    @Mock
    private Texture aliveTextureMock;

    @Mock
    private Texture frozenTextureMock;

    @Mock
    private Texture deadTextureMock;
    @Mock
    private Sprite spriteMock;
    
    @Mock
    private List<Texture> defaultTextureMock;

    @Mock
    private List<Texture>  attacksTextureMock;
    private Cat cat;   
    private Rat rat;
    private LinkedList<Rat> rats = new LinkedList<>();


    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        defaultTextureMock = new ArrayList<>();
        attacksTextureMock = new ArrayList<>();
        Texture defaultMockTexture = createMockTexture(100, 100);
        Texture attackMockTexture = createMockTexture(100, 100);
        defaultTextureMock.add(defaultMockTexture);
        attacksTextureMock.add(attackMockTexture);
        cat = new AttackCat(40, 100, defaultTextureMock, attacksTextureMock, 25.0f, 200);
        rat = new Rat(100, 10, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock);
        cat = setupCatWithSpriteAtPosition(5, 5);
    }
    
    private Texture createMockTexture(int width, int height) {
        Texture texture = mock(Texture.class);
        when(texture.getWidth()).thenReturn(width);
        when(texture.getHeight()).thenReturn(height);
        return texture;
    }

    @Test
        void getUpgradeCounterTest() {
        assertEquals(0, cat.getUpgradeCounter(), "Upgrade counter should be initially 0");
        cat.upgradeTexture(); 
        assertEquals(1, cat.getUpgradeCounter(), "Upgrade counter should be 1 after one upgrade");
    }

    @Test
    void setUpgradeCounterTest(){
        cat.setUpgradeCounter(3);
        assertEquals(3, cat.getUpgradeCounter(), "Upgrade counter should be 3");
    }

   @Test
   void getSizeTest(){
       assertEquals(100, cat.getSize(), "Size should be 100");
    }

    //tester ikke for at bilde endres, og den upgradeTexture()
    @Test
    void firstUpgradeTextureTest() {
        int catSizeBeforeUpgrade = cat.getSize();
        cat.setUpgradeCounter(1);
        cat.upgradeTexture();
        assertEquals(2, cat.getUpgradeCounter(), "Upgrade counter should be 2 after one upgrade");
        assertEquals(catSizeBeforeUpgrade + 30, cat.getSize());
    }

    @Test
    void secondUpgradeTextureTest() {
        int catSizeBeforeUpgrade = cat.getSize();
        cat.upgradeTexture();
        cat.upgradeTexture();
        cat.upgradeTexture();
        cat.upgradeTexture();
        assertEquals(4, cat.getUpgradeCounter(), "Upgrade counter should be 4 after one upgrade");
        assertEquals(catSizeBeforeUpgrade + 60, cat.getSize());
    }

    @Test
    void trikkerAttackImageTest(){
        float deltaTime = 0.5f;
        cat.triggerAttackImage();
        assertEquals(PictureSwapper.ATTACK, cat.getCurrentState());
        assertEquals(deltaTime,cat.getAttackImageTimer());
    }

    @Test 
    void getAttackImageTimerTest(){
        assertEquals(0, cat.getAttackImageTimer());
    }

    @Test
    void resetAttackTimerTest(){
        cat.resetAttackTimer();
        assertEquals(0, cat.getAttackImageTimer());
    }

    @Test
    void getAttackTimerTest(){
        cat.resetAttackTimer();
        assertEquals(25.0f, cat.getAttackTimer());
    }

    @Test
    void updateAnimationTest() {
        float deltaTime = 0.1f;
        float initialFireRate = 25.0f; 
        cat.resetAttackTimer();
        cat.triggerAttackImage(); 
        float initialAttackImageTimer = cat.getAttackImageTimer();

        assertEquals(initialFireRate, cat.getAttackTimer());
        cat.updateAnimation(deltaTime);

        assertEquals(initialAttackImageTimer - deltaTime, cat.getAttackImageTimer());
        assertTrue(cat.getAttackTimer() < initialFireRate && cat.getAttackTimer() >= initialFireRate - deltaTime);
        
        while (cat.getAttackImageTimer() > 0) {
                cat.updateAnimation(deltaTime);
        }

        assertEquals(PictureSwapper.DEFAULT, cat.getCurrentState());
    }

    @Test
    void upgradeDamageTest(){
        Integer initialStrength = cat.getStrength();
        cat.upgradeDamage();
        assertEquals(initialStrength * 1.25, cat.getStrength());
    }

    @Test
    void upgradeRangeTest(){
        int initialRange = cat.getRange();
        Circle initialRangeCirle = cat.getRangeCircle();
        cat.upgradeRange();
        assertEquals(initialRangeCirle.radius * 1.25, cat.getRangeCircle().radius);
        assertEquals(initialRange * 1.25, cat.getRange());
    }

    @Test
    void upgradeFireRateTest(){
        float initialFireRate = cat.getFireRate();
        cat.upgradeFireRate();
        assertEquals(initialFireRate * 0.75, cat.getFireRate());
    }

   
    @Test
    void attackTest(){
        for (int i = 0; i < 3; i++) {
            rats.add(rat);
        }
        int initialRatHealth = rat.getHealth();
        cat.attack(rats);
        assertEquals(initialRatHealth - cat.getStrength(), rat.getHealth());
    }

    @Test
    void setPosTest(){
        cat.setPos(10, 10);
        assertEquals(10, cat.getPosition().x);
        assertEquals(10, cat.getPosition().y);
    }

    @Test
    void getRangeCircleTest(){
        assertEquals(100, cat.getRangeCircle().radius);
    }

    @Test
    void setLastTargetPositionTest(){
        cat.setLastTargetPosition(new Vector2(10, 10));
        assertEquals(10, cat.getLastTargetPosition().x);
        assertEquals(10, cat.getLastTargetPosition().y);
    }

    @Test
    void getLastTargetPositionTest(){
        cat.setLastTargetPosition(new Vector2(10, 10));
        assertEquals(10, cat.getLastTargetPosition().x);
        assertEquals(10, cat.getLastTargetPosition().y);
    }

    private Cat setupCatWithSpriteAtPosition(float x, float y) {
        Texture defaultTexture = mock(Texture.class);
        Texture attackTexture = mock(Texture.class);
        List<Texture> defaultTextures = new ArrayList<>();
        List<Texture> attackTextures = new ArrayList<>();
        defaultTextures.add(defaultTexture);
        attackTextures.add(attackTexture);
        cat.setPos(x, y);
        cat.getSprite().setBounds(0, 0, 10, 10);
        cat.getSprite().setPosition(x, y);
    
        return cat;
    }

    private float calculateExpectedAngle(float catX, float catY, float targetX, float targetY) {
        float dx = targetX - catX;
        float dy = targetY - catY;
        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
        return angle - 90;
    }

    @Test 
    void rotateImageTest(){
        Cat cat = setupCatWithSpriteAtPosition(0, 0);
        Vector2 targetPosition = new Vector2(10, 10);
        cat.setLastTargetPosition(targetPosition);
        cat.rotateImage();
        float expectedAngle = calculateExpectedAngle(0, 0, 10, 10);
        
        assertEquals(expectedAngle, cat.getSprite().getRotation());
        cat.setPos(5, 5); 
        cat.setLastTargetPosition(new Vector2(15, 15)); 
        cat.rotateImage();
        expectedAngle = calculateExpectedAngle(5, 5, 15, 15);
        
        assertEquals(expectedAngle, cat.getSprite().getRotation());
    }



    //test for setRotationToward

    @Test
    void setRotationTowardTest(){
        Cat cat = setupCatWithSpriteAtPosition(0, 0);
        rat.setPosition(new Vector2(10, 10));
        cat.setRotationToward(rat);
        float expectedAngle = calculateExpectedAngle(0, 0, 10, 10);
        //assertEquals(expectedAngle, cat.getSprite().getRotation());
    }

    @Test
    void circleUpdaterTest(){
        cat.circleUpdater();
        assertEquals(100, cat.getRangeCircle().radius);
    }

    @Test
    void getRotationAngleTest(){
        cat.setRotationToward(rat);
        float expectedAngle = calculateExpectedAngle(5, 5, 10, 10);
        //assertEquals(expectedAngle, cat.getRotationAngle());
    }

    









    


    

}
