package inf112.skeleton.app.model.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.badlogic.gdx.Gdx.*;


public class CatTest {

    private static Cat cat;
    private static Rat rat;
    private static final float CAT_RANGE = 100.0f; // Example range value

    @BeforeAll
    public static void setUp() {
        // Assuming the Cat constructor takes strength, range, and an image (null for
        // simplicity)
        cat = new Cat(10, CAT_RANGE, null);
        rat = new Rat(10, 10, null); // Assuming Rat has a default constructor
    }

    @Test
    public void testWithinRangeTrue() {
        // Place the rat within the cat's range

        cat.setPos(rat.getPosition());

        assertTrue(cat.withinRange(rat), "Rat should be within range");
    }

    @Test
    public void testWithinRangeFalse() {
        // Place the rat outside the cat's range

        cat.setPos(rat.getPosition());

        assertFalse(cat.withinRange(rat), "Rat should not be within range");
    }
}
