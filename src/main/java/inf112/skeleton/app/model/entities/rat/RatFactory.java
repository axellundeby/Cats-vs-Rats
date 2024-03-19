package inf112.skeleton.app.model.entities.rat;

import java.util.ArrayList;

public class RatFactory {
    private int round = 1;

    public  ArrayList<Rat> spawnRats() {
        ArrayList<Rat> rats = new ArrayList<>();
        int numberOfRats = calculateBalloonsForRound(round);

        for (int i = 0; i < numberOfRats; i++) {
            rats.add(new BasicRat());
            //kanskje adde noen andre typer rotter også
        }

        round++;
        return rats;
    }

    public int calculateBalloonsForRound(int round) {
        return 10 + (round * 5);
    }
}
