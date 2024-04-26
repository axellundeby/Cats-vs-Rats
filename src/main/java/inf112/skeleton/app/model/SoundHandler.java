
// package inf112.skeleton.app.model;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.audio.Sound;
// import com.badlogic.gdx.utils.Disposable;

// public class SoundHandler implements Disposable {
//     private Sound basicCatAttackSound;
//     private Sound ratSpawnSound;
//     private Sound lifeLostSound;
//     private Sound upgradeSound;
//     private Sound coinSpawnSound;

//     public SoundHandler() {
//         // Load sound files
//         basicCatAttackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cat_attack.mp3"));
//         ratSpawnSound = Gdx.audio.newSound(Gdx.files.internal("sounds/rat_spawn.mp3"));
//         lifeLostSound = Gdx.audio.newSound(Gdx.files.internal("sounds/life_lost.mp3"));
//         upgradeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/upgrade.mp3"));
//         coinSpawnSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin_spawn.mp3"));
//     }

//     public void playBasicCatAttack() {
//         basicCatAttackSound.play();
//     }

//     public void playShotgunCatAttack() {
//         basicCatAttackSound.play();
//     }

//     public void playRatSpawn() {
//         ratSpawnSound.play();
//     }

//     public void playLifeLost() {
//         lifeLostSound.play();
//     }

//     public void playUpgrade() {
//         upgradeSound.play();
//     }

//     public void playCoinSpawn() {
//         coinSpawnSound.play();
//     }

//     @Override
//     public void dispose() {
//         // Dispose all sounds to free up resources
//         basicCatAttackSound.dispose();
//         ratSpawnSound.dispose();
//         lifeLostSound.dispose();
//         upgradeSound.dispose();
//         coinSpawnSound.dispose();
//     }
// }
