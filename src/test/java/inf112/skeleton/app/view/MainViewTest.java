package inf112.skeleton.app.view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class MainViewTest {

    private static SkadedyrModel model;
    @Mock
    private static SkadedyrView view;
    private static Texture mockTexture;
    private static TimeSource mockTimeSource;
    private static GameResourceFactory mockFactory;
    private static Sound mockSound;
    private static ApplicationListener listener;

    private GL20 gl20Mock;

    @BeforeAll
    static void setUpBeforeAll() {

        mockFactory = mock(GameResourceFactory.class);
        mockTexture = mock(Texture.class);
        mockTimeSource = mock(TimeSource.class);

        model = new SkadedyrModel(mockFactory, mockTimeSource);
        view = new SkadedyrView(model);

        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        listener = new ApplicationListener() {

            @Override
            public void create() {
                view.create();
            }

            @Override
            public void resize(int width, int height) {
                view.resize(width, height);
            }

            @Override
            public void render() {
                view.render();
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
                view.dispose();
            }
        };
        new HeadlessApplication(listener, config);

    }

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        gl20Mock = mock(GL20.class);
        Gdx.gl20 = gl20Mock;
        Gdx.gl = gl20Mock;
    }

    @Test
    public void testInitialization() {

        listener.create();

        assertNotNull(view);

    }

    @Test
    public void testDispose() {
        listener.dispose();

        when(mockFactory.getTexture(anyString())).thenReturn(mockTexture);
        Cat mockCat = mock(Cat.class);
        when(mockCat.getTexture()).thenReturn(mockTexture);
        Rat mockRat = mock(Rat.class);
        when(mockRat.getTexture()).thenReturn(mockTexture);
        model.addCat(mockCat);
        model.addRat(mockRat);

        verify(mockTexture, times(0)).dispose();
        verify(mockCat, atLeast(0)).getTexture();
        verify(mockRat, atLeast(0)).getTexture();

    }

    @Test
    public void testRender() {
        listener.render();
    }

    @Test
    public void resourceFactoryTextureTest(){

        when(mockFactory.getTexture(anyString())).thenReturn(mockTexture);
        Texture tex = mockFactory.getTexture(anyString());

        assertEquals(mockTexture, tex);
    }

    @Test
    public void resourceFactorySoundTest(){

        when(mockFactory.getSound(anyString())).thenReturn(mockSound);
        Sound sound = mockFactory.getSound(anyString());

        assertEquals(mockSound, sound);
    }

    @CsvSource(value = {"0", "1", "0.0001"})
    @ParameterizedTest(name = "Float: {0}")
    public void timeSourceTest(Float expected){
        when(mockTimeSource.getDeltaTime()).thenReturn(expected);
        Float actual = mockTimeSource.getDeltaTime();
        assertEquals(expected, actual);
    }

}
