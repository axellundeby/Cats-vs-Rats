package inf112.skeleton.app.model.entities.rat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;



public class RatTest {

    @Mock
    private Texture aliveTextureMock;

    @Mock
    private Texture frozenTextureMock;

    @Mock
    private Texture deadTextureMock;
    private Rat rat;
    private Vector2[] controlPoints;
    private int halfSize;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controlPoints = new Vector2[] {
            new Vector2(-10,290),
            new Vector2(8,290),
            new Vector2(200,290),
            new Vector2(200,422),
            new Vector2(85,422), 
            new Vector2(85,616), 
            new Vector2(106,616),
            new Vector2(435,620),
            new Vector2(435,290),
            new Vector2(654,290),
            new Vector2(654,360),
            new Vector2(875,360),
            new Vector2(875,490),
            new Vector2(660,490),
            new Vector2(660,610),
            new Vector2(1080,610),
            new Vector2(1080,310),
            new Vector2(1200,310),
            new Vector2(1500,310),
        };
        rat = new Rat(100, 10, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock);
        rat.setPosition(controlPoints[0]); 
        this.halfSize = 25;
    }
    


    @Test
    void testInitialHealth() {
        assertEquals(100, rat.getHealth());
    }

   
    private Vector2 calculateExpectedPosition(float deltaTime, int startControlPoint, Vector2 startPosition, float speed) {
        Vector2 expectedPosition = new Vector2(startPosition); 
        int tempControlPoint = startControlPoint; 
    
        while (deltaTime > 0 && tempControlPoint < controlPoints.length - 1) {
            Vector2 currentPoint = controlPoints[tempControlPoint];
            Vector2 nextPoint = controlPoints[tempControlPoint + 1];
            Vector2 directionToNextPoint = new Vector2(nextPoint).sub(currentPoint).nor();
            float distanceToNextPoint = currentPoint.dst(nextPoint);
            float distanceToMove = speed * deltaTime;
    
            if (distanceToMove >= distanceToNextPoint) {
                deltaTime -= distanceToNextPoint / speed;
                expectedPosition.set(nextPoint);
                tempControlPoint++;
            } else {
                expectedPosition.add(directionToNextPoint.scl(distanceToMove));
                deltaTime = 0;
            }
        }
    
        return expectedPosition;
    }
    
    
    @Test
    void testRatMovementFromStart() {
        float deltaTime = 0.016f;
        Vector2 startPos = controlPoints[0]; 
        rat.setPosition(startPos);
        rat.setEffectiveSpeed(10); 
    
        rat.moveAlongPath(deltaTime);
        Vector2 expectedPosition = calculateExpectedPosition(deltaTime, 0, startPos, rat.getEffectiveSpeed());
        Vector2 actualPosition = rat.getPosition();
    
        float delta = 0.16f;
        assertEquals(expectedPosition.x, actualPosition.x, delta, "X coordinates do not match");
        assertEquals(expectedPosition.y, actualPosition.y, delta, "Y coordinates do not match");
    }

    @Test
    void testRatMidPathMovement() {
        Vector2 midPathStartPos = controlPoints[2]; 
        rat.setPosition(midPathStartPos);
        float midPathSpeed = 15f; 
        rat.setEffectiveSpeed(midPathSpeed);

        float deltaTime = 0.016f;
        rat.moveAlongPath(deltaTime);
        Vector2 expectedPosition = calculateExpectedPosition(deltaTime, 2, midPathStartPos, rat.getEffectiveSpeed());
        Vector2 actualPosition = rat.getPosition();

        float delta = 0.2f;
        assertEquals(expectedPosition.x, actualPosition.x, delta, "X coordinates do not match");
        assertEquals(expectedPosition.y, actualPosition.y, delta, "Y coordinates do not match");
    }

    @Test
    void testSetEffectiveSpeed() {
        float initialSpeed = rat.getEffectiveSpeed();
        float newSpeed = 20f;  
        rat.setEffectiveSpeed(newSpeed);
        float updatedSpeed = rat.getEffectiveSpeed();
        assertNotEquals(initialSpeed, updatedSpeed, "Initial and updated speeds should not be the same");
        assertEquals(newSpeed, updatedSpeed, "The effective speed should be updated to the new speed");
    }


    @Test
    void testCreatePath() {
        rat.createPath();
        assertNotNull(rat.getControlPoints(), "Control points should not be null after path creation");
        assertTrue(rat.getControlPoints().length > 0, "Control points should have elements after path creation");
        assertNotNull(rat.getPath(), "Path should be initialized after creating control points");
        Vector2 expectedFirstPoint = new Vector2(-10, 290);
        Vector2 expectedLastPoint = new Vector2(1500, 310);
        assertEquals(expectedFirstPoint, rat.getControlPoints()[0], "First control point should match expected value");
        assertEquals(expectedLastPoint, rat.getControlPoints()[rat.getControlPoints().length - 1], "Last control point should match expected value");
    }


    @Test 
    void testGetPath() {
        rat.createPath(); 
        CatmullRomSpline<Vector2> path = rat.getPath();
        assertNotNull(path, "Path should not be null after path creation");
        assertFalse(path.spanCount > 0 && path.continuous, "Path should be set to non-looping by default");
    }

    @Test
    void testGetControlPoints() {
        rat.createPath(); 
        Vector2[] controlPoints = rat.getControlPoints();
        assertNotNull(controlPoints, "Control points should not be null after path creation");
        assertTrue(controlPoints.length > 0, "Control points should have elements after path creation");
        assertEquals(new Vector2(-10, 290), controlPoints[0], "First control point should match expected");
        assertEquals(new Vector2(1500, 310), controlPoints[controlPoints.length - 1], "Last control point should match expected");
    }
    
    @Test
    void testGetPosition() {
        Vector2 initialPos = new Vector2(-10, 430);
        rat.setPosition(initialPos);
        assertEquals(initialPos, rat.getPosition(), "Initial position should match the set position");
        Vector2 newPos = new Vector2(100, 200);
        rat.setPosition(newPos);
        assertEquals(newPos, rat.getPosition(), "Position should be updated to the new position");
    }

    
    @Test
    void testImageDirectionAndRotationForRightMovment(){
        int startPointIndex = 0; 
        rat.setPosition(controlPoints[startPointIndex]);
        rat.currentControlPoint = startPointIndex;
        rat.setEffectiveSpeed(10);
        float deltaTime = 0.1f; 
        rat.moveAlongPath(deltaTime);
        float expectedRotationWhennMovingUp = -90; 
        Sprite sprite = rat.getSprite();
        Direction expectedDirectionWhenMovingUp = Direction.RIGHT; 
        Direction actualDirection = rat.getDirection(); 
        assertEquals(expectedDirectionWhenMovingUp, actualDirection, "The direction should be updated correctly after movement.");
        assertEquals(expectedRotationWhennMovingUp, sprite.getRotation(), "The sprite's rotation should match the expected value.");
    }
    

        @Test
    void testImageDirectionAndRotationForUpMovment(){
        int startPointIndex = 2; 
        rat.setPosition(controlPoints[startPointIndex]);
        rat.currentControlPoint = startPointIndex;
        rat.setEffectiveSpeed(10);
        float deltaTime = 0.1f; 
        rat.moveAlongPath(deltaTime);
        float expectedRotationWhennMovingUp = 0; 
        Sprite sprite = rat.getSprite();
        Direction expectedDirectionWhenMovingUp = Direction.UP; 
        Direction actualDirection = rat.getDirection(); 
        assertEquals(expectedDirectionWhenMovingUp, actualDirection, "The direction should be updated correctly after movement.");
        assertEquals(expectedRotationWhennMovingUp, sprite.getRotation(), "The sprite's rotation should match the expected value.");
    }

    @Test
    void testImageDirectionAndRotationForLeftMovment(){
        int startPointIndex = 3; 
        rat.setPosition(controlPoints[startPointIndex]);
        rat.currentControlPoint = startPointIndex;
        rat.setEffectiveSpeed(10);
        float deltaTime = 0.1f; 
        rat.moveAlongPath(deltaTime);
        float expectedRotationWhennMovingUp = 90; 
        Sprite sprite = rat.getSprite();
        Direction expectedDirectionWhenMovingUp = Direction.LEFT; 
        Direction actualDirection = rat.getDirection(); 
        assertEquals(expectedDirectionWhenMovingUp, actualDirection, "The direction should be updated correctly after movement.");
        assertEquals(expectedRotationWhennMovingUp, sprite.getRotation(), "The sprite's rotation should match the expected value.");
    }

    @Test
    void testImageDirectionAndRotationForDownMovment(){
        int startPointIndex = 7; 
        rat.setPosition(controlPoints[startPointIndex]);
        rat.currentControlPoint = startPointIndex;
        rat.setEffectiveSpeed(10);
        float deltaTime = 0.1f; 
        rat.moveAlongPath(deltaTime);
        float expectedRotationWhennMovingUp = 180; 
        Sprite sprite = rat.getSprite();
        Direction expectedDirectionWhenMovingUp = Direction.DOWN; 
        Direction actualDirection = rat.getDirection(); 
        assertEquals(expectedDirectionWhenMovingUp, actualDirection, "The direction should be updated correctly after movement.");
        assertEquals(expectedRotationWhennMovingUp, sprite.getRotation(), "The sprite's rotation should match the expected value.");
    }

    @Test
    void isOutTest() {
        assertFalse(rat.isOut());
        rat.setPosition(new Vector2(1150, 310));
        assertTrue(rat.isOut());
    }

    @Test 
    void isrewardClaimedTest() {
        assertFalse(rat.isrewardClaimed());
        rat.rewardClaimed();
        assertTrue(rat.isrewardClaimed());
    }

    @Test
    void isExitedTest() {
        assertFalse(rat.isExited());
        rat.exit();
        assertTrue(rat.isExited());
    }


    @Test
    void getBountyTest(){
        assertEquals(50, rat.getBounty());
    }

    @Test
    void getPointsTest(){
        assertEquals(20, rat.getPoints());
    }


    @Test
    void getTextureTest(){
        assertEquals(aliveTextureMock, rat.getTexture());
    }

    @Test
    void getRectangleTest(){
        rat.setPosition(new Vector2(100, 100));
        System.out.println(rat.getPosition());
        assertEquals(100 - halfSize, rat.getRectangle().x);
        assertEquals(100 - halfSize, rat.getRectangle().y);
        assertEquals(halfSize * 2, rat.getRectangle().width);
        assertEquals(halfSize * 2, rat.getRectangle().height);
    }    


    @Test
    void testTakeDamage(){
        rat.takeDamage(10);
        assertEquals(90, rat.getHealth());
    }

    @Test
    void testRotateImageDown() {
        rat.setDirection(Direction.DOWN);
        rat.rotateImage();
        assertEquals(180, rat.getSprite().getRotation(), "Sprite rotation should be 180 degrees for DOWN direction");
    }


    @Test
    void testRotateImageUp() {
        rat.setDirection(Direction.UP);
        rat.rotateImage();
        assertEquals(0, rat.getSprite().getRotation(), "Sprite rotation should be 180 degrees for DOWN direction");
    }


    @Test
    void setDirectionTest() {
        rat.setDirection(Direction.UP);
        assertEquals(Direction.UP, rat.getDirection());
        rat.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, rat.getDirection());
        rat.setDirection(Direction.RIGHT);
        assertEquals(Direction.RIGHT, rat.getDirection());
        rat.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, rat.getDirection());
        rat.setDirection(Direction.OUT);
        assertEquals(Direction.OUT, rat.getDirection());
    }


    @Test
    void getSpriteTest() {
        Sprite sprite = rat.getSprite();
        assertEquals(aliveTextureMock, sprite.getTexture(), "Sprite texture should be initialized with the 'alive' texture");
        assertEquals(halfSize * 2, sprite.getWidth(), "Sprite width should be correctly set based on halfSize");
        assertEquals(halfSize * 2, sprite.getHeight(), "Sprite height should be correctly set based on halfSize");
        Vector2 expectedPosition = new Vector2(rat.getPosition().x - halfSize, rat.getPosition().y - halfSize);
        assertEquals(expectedPosition.x, sprite.getX(), "Sprite X coordinate should match the expected initial position");
        assertEquals(expectedPosition.y, sprite.getY(), "Sprite Y coordinate should match the expected initial position");
    }

    @Test 
    void killedAnimationTest() {
        rat.takeDamage(100);
        assertEquals(0, rat.getHealth());
        assertTrue(rat.isKilled(), "Rat should be marked as killed");
        assertEquals(deadTextureMock, rat.getTexture());
    }

    @Test
    void updateCoinVisibilityTest() {
        float coin = rat.getCoinVisibleTime();
        rat.takeDamage(100);
        rat.updateCoinVisibility(50);
        assertEquals(coin + 50, rat.getCoinVisibleTime());
    }

    @Test
    void getCoinVisibleTimeTest() {
        assertEquals(0, rat.getCoinVisibleTime());
    }

    @Test
    void isKilledTest(){
        assertFalse(rat.isKilled());
        rat.takeDamage(100);
        assertTrue(rat.isKilled());
    }

    @Test
    void setPositionTest(){
        Vector2 pos = new Vector2(100, 100);
        rat.setPosition(pos);
        assertEquals(pos, rat.getPosition());
        assertEquals(pos.x - halfSize, rat.getRectangle().x);
        assertEquals(pos.y - halfSize, rat.getRectangle().y);
    }
         

    @Test 
    void getHealthTest(){
        assertEquals(100, rat.getHealth());
    }

    @Test
    void getPositionTest(){
        Vector2 pos = new Vector2(100, 100);
        rat.setPosition(pos);
        assertEquals(pos, rat.getPosition());
    }

    @Test
    void testFreezing() {
        assertFalse(rat.isFrozen());
        rat.freeze(5); 
        assertEquals(frozenTextureMock, rat.getTexture());
        assertTrue(rat.isFrozen());
        rat.unfreeze();
        assertEquals(aliveTextureMock, rat.getTexture());
        assertFalse(rat.isFrozen());
    }

    @Test
    void getEffectiveSpeedTest() {
        assertEquals(10, rat.getEffectiveSpeed());
    }






}
