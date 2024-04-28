package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundResourceFactory {

    public Sound getSound(String soundFile) {
        return Gdx.audio.newSound(Gdx.files.internal(soundFile));
    }
}
