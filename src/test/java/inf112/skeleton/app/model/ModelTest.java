package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.LabCat;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.LabRat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class ModelTest {
    static SkadedyrModel model;
    private Texture dependency;

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationListener() {

            @Override
            public void create() {
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }
        };
        new HeadlessApplication(listener, config);

    }

    @BeforeEach
    void beforeEach() {
        model = new SkadedyrModel();
        dependency = mock(Texture.class);

    }

    @Test
    public void testAddCat() {
        Cat cat = new LabCat(0, 0, dependency, dependency, 0, 0);
        assertEquals(0, model.getCats().size(), "Initial Cats list is not empty");
        model.addCat(cat);
        int actual = model.getCats().size();
        assertEquals(1, actual, "Expected one cat in list, but got " + actual);

    }

    @Test
    public void testAddRat() {
        Rat rat = new LabRat(0, 0, dependency, 1, 1);

        assertEquals(0, model.getCats().size(), "Initial Rats list is not empty");
        model.addRat(rat);
        int actual = model.getRats().size();
        assertEquals(1, actual, "Expected one cat in list, but got " + actual);

    }

    @Test
    public void ratRemovalTest(){
        // Add 10 dead rats to the game
        for (int i = 0; i < 10; i++) {
            model.addRat(new LabRat(0, 1, dependency, 1, 1));
        }
        // Test rat list sizes
        assertEquals(10, model.getRats().size());
        // Removes dead rats
        model.removeDeadOrExitedRats(0);
        assertEquals(0, model.getRats().size());
    }

    @Test
    public void attackTest(){
        for (int i = 0; i < 10; i++) {
            model.addRat(new LabRat(1, 1, dependency, 1, 1));
            model.addCat(new LabCat(10, 10, dependency, dependency, 1, 0));
        }
        
    }

    @Test
    public void attackQueueTest1() {
        // Create and add mocks to model
        Cat mockCat = mock(BasicCat.class);

        model.addCat(mockCat);

        Rat mockRat = mock(BasicRat.class);

        model.addRat(mockRat);

        // When asked if rat is within range, return true
        when(mockCat.withinRange(mockRat)).thenReturn(true);

        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();

        LinkedList<Rat> ratQue = attackMap.get(mockCat);

        int expected = mockRat.hashCode();
        int actual = ratQue.getFirst().hashCode();
        assertEquals(expected, actual, "Wrong rat targeted for attack");
    }

    @Test
    public void attackQueueTest2() {
        // Create mocks
        Cat mockCat = mock(BasicCat.class);

        model.addCat(mockCat);

        // Mock rats as well
        Rat rat1 = mock(BasicRat.class);
        Rat rat2 = mock(BasicRat.class);

        model.addRat(rat1);
        model.addRat(rat2);

        // When asked if rat is within range, return true
        // Rat 1 is closer to the cat than rat 2, but both are in range
        when(mockCat.withinRange(rat1)).thenReturn(true);
        when(mockCat.withinRange(rat2)).thenReturn(true);

        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();

        LinkedList<Rat> ratQue = attackMap.get(mockCat);

        int expected = rat1.hashCode();
        int actual = ratQue.getFirst().hashCode();
        assertEquals(expected, actual, "Wrong rat targeted for attack");

        expected = rat2.hashCode();
        actual = ratQue.getLast().hashCode();
        assertEquals(expected, actual, "Wrong rat targeted for attack");
    }

    @Test
    public void attackQueueTest3() {
        // Thrid scenario where one rat is close to the cat and one is out of range
        Cat mockCat = mock(BasicCat.class);
        Rat ratClose = mock(BasicRat.class),
            ratFar = mock(BasicRat.class);

        // Check that only the rat within range of the cat is taken into account
        when(mockCat.withinRange(ratClose)).thenReturn(true);
        when(mockCat.withinRange(ratFar)).thenReturn(false);

        model.addCat(mockCat);
        model.addRat(ratClose);
        model.addRat(ratFar);

        // Execute the method under test
        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();

        // Verify outcomes
        assertTrue(attackMap.containsKey(mockCat), "Attack is missing cat");
        LinkedList<Rat> assignedRats = attackMap.get(mockCat);
        assertNotNull(assignedRats, "List of rats missing for the cat");
        assertTrue(assignedRats.contains(ratClose), "The close rat should be in the attack queue");
        assertFalse(assignedRats.contains(ratFar), "The far rat should not be in the attack queue");
    }

    @Test
    public void pauseToggleTest() {
        assertTrue(model.isPaused(), "Game should start in a paused state");
        model.setPause();
        assertFalse(model.isPaused(), "Game should be unpaused after calling setPause");
    }



}
