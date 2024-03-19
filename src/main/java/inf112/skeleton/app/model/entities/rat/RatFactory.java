package inf112.skeleton.app.model.entities.rat;
import java.util.ArrayList;

public class RatFactory {
    private int round = 1;
    private float spawnTimer = 0;
    private int ratsSpawned = 0;
    private static final int RAT_SPAWN_DELAY = 5; 
    
    public void updateRatFactory(float deltaTime) {
        spawnTimer += deltaTime;
        if (spawnTimer > RAT_SPAWN_DELAY) {
            spawnTimer = 0; 
            spawnRats(); 
        }
    }

    public void spawnRats() {
        ArrayList<Rat> rats = new ArrayList<>();
        int numberOfRats = calculateBalloonsForRound(round);

        for (int i = 0; i < numberOfRats; i++) {
            rats.add(new BasicRat());
        }
        ratsSpawned += numberOfRats;
    }

    public int calculateBalloonsForRound(int round) {
        return 10 + (round * 5);
    }
}
