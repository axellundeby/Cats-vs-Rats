package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyFloat;
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

    @BeforeEach
    public void setUp() {
        gsm = Mockito.mock(GameStateManager.class);
        model = Mockito.mock(SkadedyrModel.class);
        resourceFactory = Mockito.mock(GameResourceFactory.class);
        spriteBatch = Mockito.mock(SpriteBatch.class);

        when(resourceFactory.createStage()).thenReturn(Mockito.mock(Stage.class));
        when(resourceFactory.getTexture(anyString())).thenReturn(Mockito.mock(Texture.class));

    
        gameOverState = new GameOverState(gsm, model, resourceFactory);
    }

    @Test
    public void testRender() {
        assertNotNull(spriteBatch, "SpriteBatch should be instantiated");

        // Preparing the color setup before rendering to simulate the alpha effect
        when(spriteBatch.getColor()).thenReturn(new Color(1, 1, 1, 0));

        // Call render method
        gameOverState.render(spriteBatch);

        // Verify that the spriteBatch.begin and spriteBatch.end methods are called
        verify(spriteBatch).begin();
        verify(spriteBatch).setColor(1f, 1f, 1f, anyFloat());
        //verify(spriteBatch).draw(any(), eq(0f), eq(0f), anyFloat(), anyFloat());
        verify(spriteBatch).setColor(1f, 1f, 1f, 1f);
        verify(spriteBatch).end();

        // Verify the clearing of the screen with specified color
        verify(resourceFactory).clearScreen(Color.WHITE);

        // It's important to test stage drawing as well
        verify(resourceFactory.createStage()).draw();
    }

}
