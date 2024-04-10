package inf112.skeleton.app.model.entities.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.rat.LabRat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class CatTest {
    static SkadedyrModel model;

    @Mock
    private Texture dependency; // Replace DependencyClass with actual dependency type

    private Cat cat;

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
        MockitoAnnotations.openMocks(this);
        model = new SkadedyrModel();
        cat = new LabCat(10, 10f, dependency, dependency, 1f, 0);
    }


    @CsvSource(value = { "10;10;10.0,10.0", "5;15;5.0,15.0", "-1;-1;-1.0,-1.0" }, delimiter = ';')
    @ParameterizedTest(name = "x = {0}, y = {1} gives [{2}]")
    public void setPositionTest(int x, int y, String vector){
        Vector2 initPos = new Vector2(cat.getPosition());
        cat.setPos(x, y);
        Vector2 posSet = cat.getPosition();
        assertFalse(initPos.equals(posSet), "Initial and Set positions are the same");
        assertEquals(posSet.toString(), "(" + vector + ")", "Set position does not match expected value");
    }
    
    @CsvSource(value = {"0,10,0.0", "10,0,-90.0", "10,10,-45.0", "-438,20,87.385574"})
    @ParameterizedTest(name = "[{0}, {1}] == {2}°")
    public void catRotationCalculationTest(int labRatX, int labRatY, float expectedAngle){
        // Create a LabRat for testing and set parameterized position
        Rat labRat = new LabRat(1, 1, dependency, 1, 1);
        labRat.setPosition(new Vector2(labRatX, labRatY));

        // Calculate rotation
        cat.setRotationToward(labRat);
        float newAngle = cat.getRotationAngle();

        assertEquals(expectedAngle, newAngle, "Angle calculation error");


    }

    @CsvSource(value = {"0,0,10,true", "100,0,74,false", "0,49,50,true", "-10,0,10,true", "-36,-36,10,false"})
    @ParameterizedTest(name = "Testing if Rat at {0}, {1} is within range {2} of Cat: {3}")
    public void rangeTest(int labRatX, int labRatY, int catRange, boolean inRange){
        Rat labRat = new LabRat(1, 1, dependency, 1, 1);
        labRat.setPosition(new Vector2(labRatX, labRatY));
        labRat.rectangleUpdater();
    
        Cat customRangeCat = new LabCat(1, catRange, dependency, dependency, 1, 0);
        customRangeCat.circleUpdater();

        System.out.println("Cat position: " + customRangeCat.getPosition());
        System.out.println("Rat position: " + labRat.getPosition());

        // Debugging outputs
        System.out.println("Cat Range Circle: " + customRangeCat.getRangeCircle());
        System.out.println("Rat Rectangle: " + labRat.getRectangle());
    
        assertEquals(inRange, customRangeCat.withinRange(labRat), "Range test failed");
    }
    


}
