package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.view.GlobalAssetManager;

public class BasicRat extends Rat {

    public BasicRat() {
        super(
            70, 
            20, 
            //GlobalAssetManager.getTexture("rats/Spill_Rotte1.png"), 
            new Texture(Gdx.files.internal("rats/Spill_Rotte1.png")),
            200, 
            1, 
            //GlobalAssetManager.getTexture("rats/Spill_Rotte1_freezed.png"), 
            new Texture(Gdx.files.internal("rats/Spill_Rotte1_freezed.png")),
            40, 
            //GlobalAssetManager.getTexture("coin.png") 
            new Texture(Gdx.files.internal("coin.png"))
        );
        @Override
        public void playSpawnkSound() {
            Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("sound/caralarm.mp3"));
            attackSound.play();
        }
    }   
}

