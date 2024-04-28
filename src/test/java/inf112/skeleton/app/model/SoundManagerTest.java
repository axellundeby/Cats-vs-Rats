package inf112.skeleton.app.model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.audio.Sound;

import inf112.skeleton.app.view.SoundResourceFactory;

public class SoundManagerTest {

    @Mock
    private SoundResourceFactory soundFactory;
    @Mock
    private Sound ratSpawnSound, coinSpawnSound, upgradeSound, hpSound;

    private SoundManager soundManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(soundFactory.getSound("sound/caralarm.mp3")).thenReturn(ratSpawnSound);
        when(soundFactory.getSound("sound/coin.mp3")).thenReturn(coinSpawnSound);
        when(soundFactory.getSound("sound/cashier.mp3")).thenReturn(upgradeSound);
        when(soundFactory.getSound("sound/hp.mp3")).thenReturn(hpSound);
    
 
        //soundManager = new SoundManager();  soundManager needs factory as argument?
    }
    
    @Test
    public void playCoinSpawnSound() {
        soundManager.playCoinSpawnSound();
        verify(coinSpawnSound).play();
    }
}
