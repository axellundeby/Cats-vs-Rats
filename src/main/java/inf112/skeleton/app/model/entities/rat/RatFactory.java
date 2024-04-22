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

    public int calculateRatsForRound(int round) {
        return 1 + (round * 5);
    }

    //krasjer etter x runder
    private Rat ratVariation(int level) {
        // Velg en rotte med variasjon innenfor nivået
        int type = random.nextInt(100); // Tilfeldig nummer mellom 0 og 99

        if (level <= 2) {
            return new BasicRat(); // Bare BasicRat på de første nivåene
        } else if (level <= 4) {
            // 80% sjanse for BasicRat, 20% for SpeedRat
            return (type < 80) ? new BasicRat() : new FastRat();
        } else if (level <= 6) {
            // 50% BasicRat, 30% SpeedRat, 20% StrongRat
            if (type < 50) return new BasicRat();
            else if (type < 80) return new FastRat();
            else return new StrongRat();
        }
        return null; 
    }

  
}
