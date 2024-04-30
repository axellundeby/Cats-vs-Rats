package inf112.skeleton.app.view;

import inf112.skeleton.app.view.States.State;
import inf112.skeleton.app.view.States.GameStateManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class StateTest {
    
    @Mock
    private GameStateManager mockGSM;
    
    @Mock
    private SpriteBatch mockBatch;
    
    private State state;

    

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        state = new TestState(mockGSM);
    }

    @Test
    public void testInitialState() {
        assertNotNull("Camera should be initialized", state.cam);
        assertNotNull("Mouse vector should be initialized", state.mouse);
        assertNotNull("GameStateManager should be assigned", state.gsm);
    }

    @Test
    public void testRender() {
        state.render(mockBatch);
        verify(mockBatch).begin(); // Assuming the batch should start drawing
        verify(mockBatch).end();   // Assuming the batch should end drawing
    }

    @Test
    public void testDispose() {
        state.dispose();
        // Here you can test if resources were cleaned up, if specific mock interactions are expected, etc.
    }
}
