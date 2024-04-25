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
import com.badlogic.gdx.scenes.scene2d.ui.List;

import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.AttackCat;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.LabRat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class ModelTest {
    static SkadedyrModel model;
    private List<Texture> dependency1;
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
        Cat cat = new AttackCat(0, 0, dependency, dependency, 0, 0);
        assertEquals(0, model.getCats().size(), "Initial Cats list is not empty");
        model.addCat(cat);
        int actual = model.getCats().size();
        assertEquals(1, actual, "Expected one cat in list, but got " + actual);

    }

    @Test
    public void testAddRat() {
        Rat rat = new LabRat(0, 0, dependency,1,1,1);

        assertEquals(0, model.getCats().size(), "Initial Rats list is not empty");
        model.addRat(rat);
        int actual = model.getRats().size();
        assertEquals(1, actual, "Expected one cat in list, but got " + actual);

    }

    @Test
    public void ratRemovalTest(){
        for (int i = 0; i < 10; i++) {
            Rat labrat = new LabRat(0, 1, dependency,1,1,1);
            labrat.coinVisibleTime = 1000;
            model.addRat(labrat);
        }
        assertEquals(10, model.getRats().size());
        model.removeDeadOrExitedRats(0);
        assertEquals(0, model.getRats().size());
    }

    @Test
    public void attackTest(){
        for (int i = 0; i < 10; i++) {
            model.addRat(new LabRat(1, 1, dependency,1,1,1));
            model.addCat(new AttackCat(10, 10, dependency, dependency, 1, 0));
        }
        
    }

    @Test
    public void attackQueueTest1() {
        Cat mockCat = mock(BasicCat.class);
        model.addCat(mockCat);
        Rat mockRat = mock(BasicRat.class);
        model.addRat(mockRat);
        when(mockCat.withinRange(mockRat)).thenReturn(true);
        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();
        LinkedList<Rat> ratQue = attackMap.get(mockCat);
        int expected = mockRat.hashCode();
        int actual = ratQue.getFirst().hashCode();
        assertEquals(expected, actual, "Wrong rat targeted for attack");
    }

    @Test
    public void attackQueueTest2() {
        Cat mockCat = mock(BasicCat.class);
        model.addCat(mockCat);
        Rat rat1 = mock(BasicRat.class);
        Rat rat2 = mock(BasicRat.class);
        model.addRat(rat1);
        model.addRat(rat2);
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
        Cat mockCat = mock(BasicCat.class);
        Rat ratClose = mock(BasicRat.class),
            ratFar = mock(BasicRat.class);
        when(mockCat.withinRange(ratClose)).thenReturn(true);
        when(mockCat.withinRange(ratFar)).thenReturn(false);

        model.addCat(mockCat);
        model.addRat(ratClose);
        model.addRat(ratFar);
        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();
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

   
    @Test
    public void clockTickTest() {
        model.clockTick();
        assertTrue(model.isPaused(), "Game should be paused after a clock tick");
    }

    @Test
    public void testPauseToggle() {
        boolean initialPauseState = model.isPaused();
        model.setPause();
        assertNotEquals(initialPauseState, model.isPaused(), "Pause state should toggle");
    }

    @Test
    public void testSpeedToggle() {
        float initialSpeed = model.getSpeed();
        model.setSpeed();
        assertNotEquals(initialSpeed, model.getSpeed(), "Speed should toggle between two values");
        model.setSpeed();
        assertEquals(initialSpeed, model.getSpeed(), "Speed should toggle between two values");
    }

    @Test
    public void testMoneyManagement() {
        int initialMoney = model.getMoney();
        model.setMoney(initialMoney + 100);
        assertEquals(initialMoney + 100, model.getMoney(), "Money should be correctly set");
    }

 
   
}
