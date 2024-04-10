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
    @CsvSource(value = { "1,1,2", "1,2,3", "2,3,5", "3,5,8", "5,8,13", "8,13,21" })
	@ParameterizedTest(name = "{0}+{1} == {2}")
	void addTest(int a, int b, int c) {
		assertEquals(c, a + b);
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
    
    


}
