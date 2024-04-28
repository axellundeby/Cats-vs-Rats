package inf112.skeleton.app.model;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ModelSoundTest {

    private SkadedyrModel skadedyrModel;
    private SoundManager soundManagerMock;


    @Before
    public void setUp() {
        skadedyrModel = new SkadedyrModel();
        soundManagerMock = mock(SoundManager.class);
        skadedyrModel.setSoundManager(soundManagerMock); // Setter method to inject the mock
    }

    
}
