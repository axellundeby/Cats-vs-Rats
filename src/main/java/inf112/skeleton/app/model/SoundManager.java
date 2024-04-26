package inf112.skeleton.app.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    // Define constants for sound file paths
    private static final String RAT_SPAWN_SOUND_PATH = "sound/caralarm.mp3";
    private static final String COIN_SPAWN_SOUND_PATH = "sound/coin.mp3";
    private static final String UPGRADE_SOUND_PATH = "sound/cashier.mp3";
    private static final String HP_SOUND_PATH = "sound/hp.mp3";


    // Define Sound objects for each sound
    private Sound ratSpawnSound;
    private Sound coinSpawnSound;
    private Sound upgradeSound;
    private Sound hpSound;

    // Singleton instance
    private static SoundManager instance;

    // Private constructor to prevent instantiation from outside
    public SoundManager() {
        // Initialize sounds
    }
    
    public void init(){
        ratSpawnSound = Gdx.audio.newSound(Gdx.files.internal(RAT_SPAWN_SOUND_PATH));
        coinSpawnSound = Gdx.audio.newSound(Gdx.files.internal(COIN_SPAWN_SOUND_PATH));
        upgradeSound = Gdx.audio.newSound(Gdx.files.internal(UPGRADE_SOUND_PATH));
        hpSound = Gdx.audio.newSound(Gdx.files.internal(HP_SOUND_PATH));

    }

    // Get the singleton instance of SoundManager
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    // Method to play rat spawn sound
    public void playRatSpawnSound() {
        ratSpawnSound.play();
    }

    // Method to play coin spawn sound
    public void playCoinSpawnSound() {
        coinSpawnSound.play();
    }

    public void playUpgradeSound() {
        upgradeSound.play(0.1f);
    }

    public void playHpSound(){
        hpSound.play();
    }

    // Dispose of sounds when they're no longer needed
    public void dispose() {
        ratSpawnSound.dispose();
        coinSpawnSound.dispose();
        upgradeSound.dispose();
    }
}
