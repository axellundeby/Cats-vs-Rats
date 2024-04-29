package inf112.skeleton.app.model.entities.rat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

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
        ArrayList<Rat> rats = ratFactory.updateRatFactory(deltaTime, 1);
        assertEquals(initialSize + 1, rats.size());
    }

    @Test
    void testResetRatFactory() {
        ratFactory.updateRatFactory(12, 1); 
        assertFalse(ratFactory.updateRatFactory(0, 1).isEmpty());
        ratFactory.resetRatFactory();
        assertTrue(ratFactory.updateRatFactory(0, 1).isEmpty());
    }

    @Test
    void testCalculateRatsForRound() {
        assertEquals(6, ratFactory.calculateRatsForRound(1));
    }

    @Test
    void testRatVariationLevels() {
        Rat ratLevel1 = ratFactory.updateRatFactory(12, 1).get(0);
        assertTrue(ratLevel1 instanceof BasicRat);

        Rat ratLevel5 = ratFactory.updateRatFactory(12, 5).get(0);
        assertTrue(ratLevel5 instanceof BasicRat || ratLevel5 instanceof FastRat || ratLevel5 instanceof StrongRat);
    }
}
