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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    private int range;


    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        defaultTextureMock = new ArrayList<>();
        attacksTextureMock = new ArrayList<>();
        Texture defaultMockTexture = createMockTexture(100, 100);
        Texture defaultMockTexture2 = createMockTexture(100, 100);
        Texture defaultMockTexture3 = createMockTexture(100, 100);
        Texture attackMockTexture = createMockTexture(100, 100);
        Texture attackMockTexture2 = createMockTexture(100, 100);
        Texture attackMockTexture3 = createMockTexture(100, 100);
        defaultTextureMock.add(defaultMockTexture);
        defaultTextureMock.add(defaultMockTexture2);
        defaultTextureMock.add(defaultMockTexture3);
        attacksTextureMock.add(attackMockTexture);
        attacksTextureMock.add(attackMockTexture2);
        attacksTextureMock.add(attackMockTexture3);
        cat = new AttackCat(40, 100, defaultTextureMock, attacksTextureMock, 25.0f, 200);
        rat = new Rat(100, 10, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock);
        cat = setupCatWithSpriteAtPosition(5, 5);
        this.range = 100;
    }
    
    private Texture createMockTexture(int width, int height) {
        Texture texture = mock(Texture.class);
        when(texture.getWidth()).thenReturn(width);
        when(texture.getHeight()).thenReturn(height);
        return texture;
    }

    @Test
        void getUpgradeCounterTest() {
        assertEquals(0, cat.getUpgradeCounter());
        cat.upgradeTexture(); 
        assertEquals(1, cat.getUpgradeCounter());
    }

    @Test
    void setUpgradeCounterTest(){
        cat.setUpgradeCounter(3);
        assertEquals(3, cat.getUpgradeCounter());
    }

   @Test
   void getSizeTest(){
       assertEquals(100, cat.getSize());
    }

    @Test
    void firstUpgradeTextureTest() {
        int catSizeBeforeUpgrade = cat.getSize();
        cat.setUpgradeCounter(1);

        Sprite sprite = cat.getSprite();
        assertEquals(defaultTextureMock.get(0), sprite.getTexture());

        cat.upgradeTexture();
        assertEquals(2, cat.getUpgradeCounter());
        assertEquals(catSizeBeforeUpgrade + 30, cat.getSize());
        assertEquals(defaultTextureMock.get(1), sprite.getTexture());
    }

    @Test
    void secondUpgradeTextureTest() {
        int catSizeBeforeUpgrade = cat.getSize();
        Sprite sprite = cat.getSprite();
        assertEquals(defaultTextureMock.get(0), sprite.getTexture());
        cat.upgradeTexture();
        cat.upgradeTexture();
        cat.upgradeTexture();
        cat.upgradeTexture();
        assertEquals(4, cat.getUpgradeCounter());
        assertEquals(catSizeBeforeUpgrade + 60, cat.getSize());
        assertEquals(defaultTextureMock.get(2), sprite.getTexture());
    }



    @Test
    void setRotationTowardTest(){
        Cat cat = setupCatWithSpriteAtPosition(0, 0);
        rat.setPosition(new Vector2(10, 10));
        cat.setRotationToward(rat);
        float expectedAngle = calculateExpectedAngle(0, 0, 10, 10);
        assertEquals(expectedAngle, cat.getSprite().getRotation());
    }

    
    @Test
    void getRotationAngleTest(){
        cat.setPos(10, 10);
        rat.setPosition(new Vector2(0, 0));
        cat.setRotationToward(rat);
        float expectedAngle = calculateExpectedAngle(10, 10, 0, 0);
        assertEquals(expectedAngle, cat.getRotationAngle());
    }
    
    @Test
    void withinRangeTest(){
        rat.setPosition(new Vector2(1000, 1000));
        cat.withinRange(rat);
        assertEquals(null, cat.getLastTargetPosition());
        cat.setPos(10, 10);
        rat.setPosition(new Vector2(10, 10));
        assertTrue(cat.withinRange(rat));
    }

    @Test
    void withinRangeTest2(){
        rat.setPosition(new Vector2(1000, 1000));
        cat.withinRange(rat);
        assertEquals(null, cat.getLastTargetPosition());
        cat.setPos(30, 30);
        rat.setPosition(new Vector2(0, 0));
        assertTrue(cat.withinRange(rat));
    }

    @Test
    void circleUpdaterTest(){
        cat.circleUpdater();
        assertEquals(range, cat.getRangeCircle().radius);
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
        float initialRangeCirleRadius = cat.getRangeCircle().radius;
        cat.upgradeRange();
        float upgradedRangeRadius = cat.getRangeCircle().radius;
        assertEquals((initialRangeCirleRadius) * 1.25 , upgradedRangeRadius);
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
        assertEquals(range, cat.getRangeCircle().radius);
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
    void setSizeTest(){
        cat.setSize(200);
        assertEquals(200, cat.getSize());
    }

    @Test
    void updateAttackTimerTest(){
        cat.resetAttackTimer();
        cat.updateAttackTimer(0.1f);
        assertEquals(24.9f, cat.getAttackTimer());
    }

    @Test
    void canAttackTest(){
        assertTrue(cat.canAttack());
        cat.resetAttackTimer();
        assertFalse(cat.canAttack());
    }
   

   @Test
   void swapImageTest(){
        assertEquals(PictureSwapper.DEFAULT, cat.getCurrentState());
         cat.swapImage(PictureSwapper.ATTACK);
         assertEquals(PictureSwapper.ATTACK, cat.getCurrentState());
         cat.swapImage(PictureSwapper.DEFAULT);
         assertEquals(PictureSwapper.DEFAULT, cat.getCurrentState());
   }

   @Test
   void getTextureTest(){
        assertEquals(defaultTextureMock.get(0), cat.getTexture());
        cat.swapImage(PictureSwapper.ATTACK);
        assertEquals(attacksTextureMock.get(0),cat.getTexture());

   }

    @Test
    void getMenuTexture(){
        assertEquals(defaultTextureMock.get(0), cat.getMenuTexture());
    }

    @Test
    void getSprite(){
        Sprite sprite = cat.getSprite();
        assertEquals(defaultTextureMock.get(0), sprite.getTexture());
        cat.swapImage(PictureSwapper.ATTACK);
        assertEquals(attacksTextureMock.get(0), sprite.getTexture());
    }

    @Test
   void getStrengthAndFireRateAndRangeTest(){
         assertEquals(40, cat.getStrength());
         assertEquals(25.0f, cat.getFireRate());
         assertEquals(100, cat.getRange());
    }

    @Test
    void getCostTest(){
        assertEquals(200, cat.getCost());
    }

    @Test 
    void getSelectionCircleTest(){
        Circle selectionCircle = new Circle(cat.getPosition(),50);
        assertEquals(selectionCircle , cat.getSelectionCircle());
    }








  
    
   







    











    


    

}
