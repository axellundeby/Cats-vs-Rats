package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class ModelTest {
    @BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationListener() {

            @Override
            public void create() {
                // TODO Auto-generated method stub

            }

            @Override
            public void resize(int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void render() {
                // TODO Auto-generated method stub

            }

            @Override
            public void pause() {
                // TODO Auto-generated method stub

            }

            @Override
            public void resume() {
                // TODO Auto-generated method stub

            }

            @Override
            public void dispose() {
                // TODO Auto-generated method stub

            }
        };
   new HeadlessApplication(listener, config);

    }
}
