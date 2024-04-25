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
    private Vector2 pos;
    private int currentControlPoint = 0;
    private Vector2[] controlPoints;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.pos = new Vector2(-10, 430); 
        Vector2[] sharedControlPoints = new Vector2[] {
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
        this.controlPoints = sharedControlPoints; 
        rat = new Rat(100, 10, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock);
        rat.setPosition(this.pos); 
    }
    


    @Test
    void testInitialHealth() {
        assertEquals(100, rat.getHealth());
    }

    @Test
    void testTakeDamage() {
        rat.takeDamage(20);
        assertEquals(80, rat.getHealth());
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
    void testDirectionUpdateForRightMovment() {
        float deltaTime = 0.01f; 
        rat.moveAlongPath(deltaTime);
        Direction expectedDirectionWhenMovingRight = Direction.RIGHT; 
        Direction actualDirection = rat.getDirection(); 
        assertEquals(expectedDirectionWhenMovingRight, actualDirection, "The direction should be updated correctly after movement.");
    }

    @Test
    void testImageRotationForRightMovment() {
        float deltaTime = 0.01f; 
        rat.moveAlongPath(deltaTime);
        float expectedRotationWhennMovingTight = -90; 
        Sprite sprite = rat.getSprite();
        assertEquals(expectedRotationWhennMovingTight, sprite.getRotation(), "The sprite's rotation should match the expected value.");
    }

    @Test
    void testDirectionUpdateForUpMovment(){
        float deltaTime = 0.03f; 
        rat.moveAlongPath(deltaTime);
        Direction expectedDirectionWhenMovingUp = Direction.UP; 
        Direction actualDirection = rat.getDirection(); 
        assertEquals(expectedDirectionWhenMovingUp, actualDirection, "The direction should be updated correctly after movement.");
    }








    



    @Test
    void testFreezing() {
        assertFalse(rat.isFrozen());
        rat.freeze(5); 
        assertTrue(rat.isFrozen());
        rat.unfreeze();
        assertFalse(rat.isFrozen());
    }
}
