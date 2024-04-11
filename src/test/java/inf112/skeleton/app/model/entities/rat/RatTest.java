package inf112.skeleton.app.model.entities.rat;

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

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.Projectile;
import inf112.skeleton.app.model.entities.rat.LabRat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class RatTest {
    static SkadedyrModel model;

    @Mock
    private Texture dependency; // Replace DependencyClass with actual dependency type

    private Rat rat;

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
        rat = new LabRat(1, 1, dependency, 1, 1);
    } 

    @CsvSource(value = {"0,0,0,0,true", "1,9,6,43,false", "1,16,10,7,true", "-50,-50,10,10,false"})
    @ParameterizedTest(name = "{0}, {1}, {2}, {3}, {4}")
    public void projectileHitTest(int px, int py, int ratx, int raty, boolean hits){
        Projectile testProjectile = new Projectile(new Vector2(px, py), new Vector2(), 0, dependency);
        rat.setPosition(new Vector2(ratx, raty));
        ArrayList<Projectile> list = new ArrayList<>();
        list.add(testProjectile);
        assertEquals(hits, rat.isHitByProjectile(list));
    }
}
