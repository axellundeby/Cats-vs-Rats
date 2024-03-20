package inf112.skeleton.app.model.entities.rat;
import java.util.ArrayList;

public class RatFactory {
    private float spawnTimer = 5;
    private int ratsSpawned = 0;
    private static final int RAT_SPAWN_DELAY = 1; 
    private ArrayList<Rat> rats = new ArrayList<Rat>();
    
    /**
     * spawns rats with space between them
     * @param deltaTime
     */
    public ArrayList<Rat> updateRatFactory(float deltaTime, Integer level) {
        spawnTimer += deltaTime;
        if (spawnTimer > RAT_SPAWN_DELAY && ratsSpawned < calculateRatsForRound(level)) {
            spawnTimer = 0; 
            rats.add(new BasicRat());
            ratsSpawned++;
        }
        return rats;
    }

    public int calculateRatsForRound(int round) {
        return 1 + (round * 5);
    }
}
