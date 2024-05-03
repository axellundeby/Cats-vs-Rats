package inf112.skeleton.app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;

public class ControllerTest {
    private static SkadedyrModel mockModel;
    private static SkadedyrController controller;
    private static GameResourceFactory mockFactory;
    private static TimeSource mockTimeSource;
    private static Timer.Task mockTask;
    private static Timer.Task mockTask2;
    @Mock
    private static Input gdxInputMock;

    @BeforeAll
    static void setup() {

        mockModel = mock(SkadedyrModel.class);
        mockFactory = mock(GameResourceFactory.class);
        mockTimeSource = mock(TimeSource.class);
        mockTask = mock(Timer.Task.class);
        mockTask2 = mock(Timer.Task.class);

        controller = new SkadedyrController(mockModel, mockFactory, mockTimeSource);

        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);

        gdxInputMock = mock(Input.class);

        // Setup for headless application for LibGDX
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
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
        }, config);
    }

    @Test
    public void initTest() {
        assertNotNull(controller);

    }

    @Test
    public void startTimerTest() {
        controller.startTimer();
        verify(mockModel, atLeast(1)).getSpeed();

        Timer.schedule(mockTask, 0f, 1.0f);

    }

    @Test
    public void modelPausedTest() {
        when(mockModel.isPaused()).thenReturn(true);
        controller.startTimer();
        Timer.schedule(mockTask2, 0f, 1.0f);

    }

    @Test
    public void gdxInputTest(){
        when(gdxInputMock.isTouched()).thenReturn(true);
        controller.startTimer();
    }
}
