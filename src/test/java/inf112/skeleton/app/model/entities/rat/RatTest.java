package inf112.skeleton.app.model.entities.rat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


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
    private float effectiveSpeed;
    private float speed;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rat = new Rat(100, 10, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock);
        this.pos = new Vector2(-10, 430);
        this.effectiveSpeed = speed;

        
        rat.createPath();
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

    public Vector2 calculateExpectedPosition(float deltaTime) {
        //rat.createPath();
        Vector2 expectedPosition = new Vector2(pos);
        int tempControlPoint = currentControlPoint;
        
        while (deltaTime > 0 && tempControlPoint < controlPoints.length - 1) {
            Vector2 currentPoint = controlPoints[tempControlPoint];
            Vector2 nextPoint = controlPoints[tempControlPoint + 1];
            Vector2 directionToNextPoint = new Vector2(nextPoint).sub(currentPoint).nor();
            float distanceToNextPoint = currentPoint.dst(nextPoint);
            float distanceToMove = effectiveSpeed * deltaTime;
            
            if (distanceToMove >= distanceToNextPoint) {
                deltaTime -= distanceToNextPoint / effectiveSpeed;
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
    void testMovement() {
        float deltaTime = 0.1f;
        rat.createPath();
        rat.moveAlongPath(deltaTime);
        assertEquals(calculateExpectedPosition(deltaTime), rat.getPosition()); 
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
