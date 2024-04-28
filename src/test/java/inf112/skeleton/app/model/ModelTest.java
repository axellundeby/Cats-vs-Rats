package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ModelTest {
    static SkadedyrModel model;
   
    
    @Test
    public void pauseToggleTest() {
        assertTrue(model.isPaused());
        model.setPause();
        assertFalse(model.isPaused());
    }

   
    @Test
    public void clockTickTest() {
        model.clockTick();
        assertTrue(model.isPaused());
    }

    @Test
    public void testPauseToggle() {
        boolean initialPauseState = model.isPaused();
        model.setPause();
        assertNotEquals(initialPauseState, model.isPaused());
    }

    @Test
    public void testSpeedToggle() {
        float initialSpeed = model.getSpeed();
        model.setSpeed();
        assertNotEquals(initialSpeed, model.getSpeed());
        model.setSpeed();
        assertEquals(initialSpeed, model.getSpeed());
    }

    @Test
    public void testMoneyManagement() {
        int initialMoney = model.getMoney();
        model.setMoney(initialMoney + 100);
        assertEquals(initialMoney + 100, model.getMoney());
    }

 
   
}
