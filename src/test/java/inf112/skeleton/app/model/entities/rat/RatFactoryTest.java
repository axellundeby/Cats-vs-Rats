package inf112.skeleton.app.model.entities.rat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.view.GameResourceFactory;

public class RatFactoryTest {

    @Mock
    private GameResourceFactory mockFactory;

    private RatFactory ratFactory;
    private Texture mockTexture;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mockTexture = mock(Texture.class);
        when(mockFactory.getTexture(anyString())).thenReturn(mockTexture);
        ratFactory = new RatFactory(mockFactory);
    }

    @Test
    void testConstructor() {
        assertNotNull(ratFactory);
    }

    @Test
    void testUpdateRatFactorySpawning() {
        float deltaTime = 11;
        int initialSize = ratFactory.updateRatFactory(0, 1).size(); 
        ArrayList<IRat> rats = ratFactory.updateRatFactory(deltaTime, 1);
        assertEquals(initialSize + 1, rats.size());
    }

    @Test
    void testResetRatFactory() {
        ratFactory.updateRatFactory(12, 1); 
        assertFalse(ratFactory.updateRatFactory(0, 1).isEmpty());
        ratFactory.removeRats();
        assertTrue(ratFactory.updateRatFactory(0, 1).isEmpty());
    }

    @Test
    void testCalculateRatsForRound() {
        assertEquals(5, ratFactory.calculateRatsForRound(1));
    }

    @Test
    void testRatVariationLevels() {
        assertTrue(ratFactory.getRats().isEmpty());
        Map<Class<?>, Integer> counts = new HashMap<>();
        counts.put(BasicRat.class, 0);
        counts.put(FastRat.class, 0);
        counts.put(StrongRat.class, 0);

        int level = 7;  
        int numRatsToTest = 1000;
        float deltaTime = 11;  

        for (int i = 0; i < numRatsToTest; i++) {
            ratFactory.updateRatFactory(deltaTime, level);
            IRat lastRat = ratFactory.getRats().get(ratFactory.getRats().size() - 1);
            counts.put(lastRat.getClass(), counts.get(lastRat.getClass()) + 1);
            assertFalse(ratFactory.getRats().isEmpty());
            ratFactory.removeRats();  
            ratFactory.resetRatFactory();
            assertTrue(ratFactory.getRats().isEmpty());
        }
        assertTrue(Math.abs(counts.get(BasicRat.class) - 400) < 50);
        assertTrue(Math.abs(counts.get(FastRat.class) - 300) < 50);
        assertTrue(Math.abs(counts.get(StrongRat.class) - 300) < 50);
    }
}




