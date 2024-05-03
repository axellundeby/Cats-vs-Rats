package inf112.skeleton.app.model.entities.rat;
import java.util.ArrayList;
import java.util.Random;

import inf112.skeleton.app.view.GameResourceFactory;

public class RatFactory {
    private float spawnTimer = 5;
    private int ratsSpawned = 0;
    private static final int RAT_SPAWN_DELAY = 10; 
    private ArrayList<IRat> rats = new ArrayList<IRat>();
    private Random random = new Random();
    private GameResourceFactory resourceFactory;

    public RatFactory(GameResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }
    
    /**
     * spawns rats with space between them
     * @param deltaTime
     */
    public ArrayList<IRat> updateRatFactory(float deltaTime, Integer level) {
        spawnTimer += deltaTime;
        if (spawnTimer > RAT_SPAWN_DELAY && ratsSpawned < calculateRatsForRound(level)) {
            spawnTimer = 0; 
            rats.add(ratVariation(level));
            ratsSpawned++;
        }
        return rats;
    }

    /**
     * Resets the rat factory
     */
    public void resetRatFactory() {
        ratsSpawned = 0;
        spawnTimer = 5;
    }


    /**
     * @return the rats
     */
    public ArrayList<IRat> getRats() {
        return rats;
    }

    /**
     * Calculates the number of rats to spawn for a given round
     * @param round
     * @return
     */
    public int calculateRatsForRound(int round) {
        return 2 + (round * 3);
    }

    private IRat ratVariation(int level) {
        int type = random.nextInt(100); 
    
        IRat newRat;
        if (level <= 2) {
            newRat = new BasicRat(resourceFactory); 
        } else if (level <= 4) {
            newRat = (type < 80) ? new BasicRat(resourceFactory) : new FastRat(resourceFactory);
        } else if (level <= 6) {
            if (type < 50) newRat = new BasicRat(resourceFactory);
            else if (type < 80) newRat = new FastRat(resourceFactory);
            else newRat = new StrongRat(resourceFactory);
        } else {
            if (type < 40) newRat = new BasicRat(resourceFactory);
            else if (type < 70) newRat = new FastRat(resourceFactory);
            else newRat = new StrongRat(resourceFactory);
        }
    
        if (newRat != null) {
            newRat.createPath();
        }
    
        return newRat;
    }
}
