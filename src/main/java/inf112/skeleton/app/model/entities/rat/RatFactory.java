package inf112.skeleton.app.model.entities.rat;
import java.util.ArrayList;
import java.util.Random;

public class RatFactory {
    private float spawnTimer = 5;
    private int ratsSpawned = 0;
    private static final int RAT_SPAWN_DELAY = 10; 
    private ArrayList<Rat> rats = new ArrayList<Rat>();
    private Random random = new Random();
    
    /**
     * spawns rats with space between them
     * @param deltaTime
     */
    public ArrayList<Rat> updateRatFactory(float deltaTime, Integer level) {
        spawnTimer += deltaTime;
        if (spawnTimer > RAT_SPAWN_DELAY && ratsSpawned < calculateRatsForRound(level)) {
            spawnTimer = 0; 
            rats.add(ratVariation(level));
            ratsSpawned++;
        }
        return rats;
    }

    /**
     * Calculates the number of rats to spawn for a given round
     * @param round
     * @return
     */
    public int calculateRatsForRound(int round) {
        return 1 + (round * 5);
    }

    private Rat ratVariation(int level) {
        // Choose a rat variation within the level
        int type = random.nextInt(100); // Random number between 0 and 99
    
        Rat newRat;
        if (level <= 2) {
            newRat = new BasicRat(); // Only BasicRat at the first levels
        } else if (level <= 4) {
            // 80% chance for BasicRat, 20% for SpeedRat
            newRat = (type < 80) ? new BasicRat() : new FastRat();
        } else if (level <= 6) {
            // 50% BasicRat, 30% SpeedRat, 20% StrongRat
            if (type < 50) newRat = new BasicRat();
            else if (type < 80) newRat = new FastRat();
            else newRat = new StrongRat();
        } else {
            newRat = null;
        }
    
        if (newRat != null) {
            newRat.createPath();
        }
    
        return newRat;
    }
}
