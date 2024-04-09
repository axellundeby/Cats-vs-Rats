package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.main.SkadedyrGame;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class ModelTest {
    static SkadedyrModel model;

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
    }

    @Test
    public void testAddCat() {
        Cat cat = mock(BasicCat.class);
        assertTrue(model.getCats().isEmpty());
        model.addCat(cat);
        assertTrue(model.getCats().size() == 1);

    }

    @Test
    public void testAddRat() {
        Rat rat = mock(BasicRat.class);
        assertEquals(0, model.getCats().size());
        model.addRat(rat);
        assertEquals(1, model.getRats().size());

    }

    @Test
    public void attackQueueTest1() {
        // Create and add mocks to model
        Cat mockCat = mock(BasicCat.class);
        model.addCat(mockCat);

        Rat mockRat = mock(BasicRat.class);
        model.addRat(mockRat);

        // When asked if rat is within rangge, return true
        when(mockCat.withinRange(mockRat)).thenReturn(true);

        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();

        LinkedList<Rat> ratQue = attackMap.get(mockCat);

        int expected = mockRat.hashCode();
        int actual = ratQue.getFirst().hashCode();
        assertEquals(expected, actual);
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
        assertEquals(expected, actual);

        expected = rat2.hashCode();
        actual = ratQue.getLast().hashCode();
        assertEquals(expected, actual);

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
        assertTrue(attackMap.containsKey(mockCat), "Attack map should contain the cat");
        LinkedList<Rat> assignedRats = attackMap.get(mockCat);
        assertNotNull(assignedRats, "There should be a list of rats for the cat");
        assertTrue(assignedRats.contains(ratClose), "The close rat should be in the attack queue");
        assertFalse(assignedRats.contains(ratFar), "The far rat should not be in the attack queue");
    }

    @Test
    public void pauseToggleTest() {
        assertTrue(model.isPaused(), "Game should start in a paused state");
        model.setPause();
        assertFalse(model.isPaused(), "Game should be unpaused after calling setPause");
    }

    @Test
    public void catRotationTest(){
        Cat mockCat = mock(BasicCat.class);
        Rat mockRat = mock(BasicRat.class); 

        when(mockRat.getPosition()).thenReturn(new Vector2(10, 15));

        mockCat.setRotationToward(mockRat);
    }

    @Test
    public void ratTest(){
        Rat mockRat = mock(BasicRat.class); 
        when(mockRat.getPosition()).thenReturn(new Vector2(0, 10));
        assertEquals(new Vector2(0, 10), mockRat.getPosition());
    }


	@CsvSource(value = { "1,1,2", "1,2,3", "2,3,5", "3,5,8", "5,8,13", "8,13,21" })
	@ParameterizedTest(name = "{0}+{1} == {2}")
	void addTest(int a, int b, int c) {
		assertEquals(c, a + b);
	}
}
