package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
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

        // Instantiate GameOverState with mocked dependencies
        gameOverState = new GameOverState(gsm, model, resourceFactory);
    }

    @Test
    public void testRender() {
        // Call render method

        assertNotNull(spriteBatch);
        // gameOverState.render(spriteBatch);

        // // Verify that the spriteBatch.begin and spriteBatch.end methods are called
        // verify(spriteBatch).begin();
        // verify(spriteBatch).draw(any(), eq(0f), eq(0f), (Float) anyFloat(), (Float) anyFloat());
        // verify(spriteBatch).end();
    }

    
    
}
