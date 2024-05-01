package inf112.skeleton.app.view;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import inf112.skeleton.app.view.States.GameOverState;
import inf112.skeleton.app.view.States.GameStateManager;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.GameResourceFactory;

public class GameOverStateTest {

    private GameOverState gameOverState;
    private GameStateManager gsm;
    private SkadedyrModel model;
    private GameResourceFactory resourceFactory;
    private SpriteBatch spriteBatch;
    private MockedStatic<Gdx> mockedGdx;  // This will now be a field

    @BeforeEach
    public void setUp() {
        mockedGdx = Mockito.mockStatic(Gdx.class);  // Initialize mocked static field
        Files files = Mockito.mock(Files.class);
        FileHandle fileHandle = Mockito.mock(FileHandle.class);
        Texture texture = Mockito.mock(Texture.class);
        
        when(files.internal(anyString())).thenReturn(fileHandle);
        when(resourceFactory.getTexture(anyString())).thenReturn(texture);
        
        when(Gdx.files).thenReturn(files);  // Properly mock the Gdx.files static method

        gsm = Mockito.mock(GameStateManager.class);
        model = Mockito.mock(SkadedyrModel.class);
        resourceFactory = Mockito.mock(GameResourceFactory.class);
        spriteBatch = Mockito.mock(SpriteBatch.class);

        gameOverState = new GameOverState(gsm, model, resourceFactory);
    }

    @Test
    public void testRender() {
        assertNotNull(spriteBatch, "SpriteBatch should be instantiated");

        // Call render method
        gameOverState.render(spriteBatch);

        // Verify that spriteBatch begin and end methods are called
        verify(spriteBatch).begin();
        verify(spriteBatch).setColor(1f, 1f, 1f, anyFloat());
        verify(spriteBatch).setColor(1f, 1f, 1f, 1f);
        verify(spriteBatch).end();

        // Verify the clearing of the screen with specified color
        verify(resourceFactory).clearScreen(Color.WHITE);

        // Verify stage drawing
        verify(resourceFactory.createStage()).draw();
    }

    @AfterEach
    public void tearDown() {
        mockedGdx.close();  // Ensure to close the mock after the test
        gameOverState.dispose();
    }
}
