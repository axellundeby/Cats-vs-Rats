package inf112.skeleton.app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    @Mock
    private static Input gdxInputMock;


    @BeforeAll
    static void setup() {
        // gdxInputMock = mock(Gdx.input.getClass());
        mockModel = mock(SkadedyrModel.class);
        mockFactory = mock(GameResourceFactory.class);
        mockTimeSource = mock(TimeSource.class);
        mockTask = mock(Timer.Task.class);

        controller = new SkadedyrController(mockModel, mockFactory, mockTimeSource);

        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);

        // Setup for headless application for LibGDX
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {}
            @Override
            public void resize(int width, int height) {}
            @Override
            public void render() {}
            @Override
            public void pause() {}
            @Override
            public void resume() {}
            @Override
            public void dispose() {}
        }, config);
    }
    
    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);

        reset(mockModel, mockFactory, mockTimeSource, mockTask);
        when(mockModel.getSpeed()).thenReturn(1.0f);
        // when(controller.clockTick()).thenReturn(mockTask);
    }

    @Test
    public void initTest() {
        assertNotNull(controller);
        
    }

    // @Test
    // public void startTimerTest() {
    //     controller.startTimer();
    //     verify(mockModel, times(1)).getSpeed();
    //     verify(mockTask, times(1)).cancel();
    //     // verifyStatic(Timer.class);
    //     Timer.schedule(eq(mockTask), eq(0f), eq(1.0f));
    // }

    // @Test
    // public void handleUserInputTest() {
    //     // Setting up the scenario
    //     when(gdxInputMock.getX()).thenReturn(100);
    //     when(gdxInputMock.getY()).thenReturn(200);
    //     when(gdxInputMock.isTouched()).thenReturn(true);
    //     when(mockModel.getCatMenu()).thenReturn(null);  // Assuming getCatMenu() is not null in real scenarios

    //     // controller.render(null);  // 'null' here because actual rendering is not tested

    //     verify(gdxInputMock, times(1)).getX();
    //     verify(gdxInputMock, times(1)).getY();
    //     verify(gdxInputMock, times(1)).isTouched();
    //     verify(mockModel, atLeastOnce()).getCatMenu();
    //     // More verifications can be added based on expected interactions
    // }
}
