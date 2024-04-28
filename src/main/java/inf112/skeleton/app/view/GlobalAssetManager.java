package inf112.skeleton.app.view;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GlobalAssetManager {
        private static final AssetManager manager = new AssetManager();
    
        // Load all necessary assets
        public static void loadAssets() {
            // Load textures
            //manager.load("rats/Spill_Rotte1.png", Texture.class);
            //manager.load("rats/Spill_Rotte1_freezed.png", Texture.class);
            //manager.load("coin.png", Texture.class);
    
     
            manager.load("sound/fart.mp3", Sound.class);
            // manager.load("sounds/ratsqueak.mp3", Sound.class);
            // manager.load("music/background_music.mp3", Music.class);
            // private static final String HP_SOUND_PATH = "sound/hp.mp3";
        }
    
        // Retrieve a texture
        public static Texture getTexture(String path) {
            return manager.get(path, Texture.class);
        }
    
        // Retrieve a sound
        public static Sound getSound(String path) {
            return manager.get(path, Sound.class);
        }
    
        // Retrieve music
        public static Music getMusic(String path) {
            return manager.get(path, Music.class);
        }
    
        // Dispose of all assets
        public static void dispose() {
            manager.dispose();
        }
    
        // Optional: check if all assets are loaded
        public static boolean update() {
            return manager.update(); // Returns true when all assets are loaded
        }
    
        // Optional: get loading progress
        public static float getProgress() {
            return manager.getProgress();
        }
}
