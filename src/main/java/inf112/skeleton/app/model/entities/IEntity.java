package inf112.skeleton.app.model.entities;

public interface IEntity {

    /**
     * Oppdaterer rottens status og posisjon basert på tidsforløpet og spilllogikken.
     * 
     * @param deltaTime Tiden siden siste frame, brukt for å sikre jevn oppdatering over ulike enheter.
     */
    void move(float deltaTime);

    /**
     * Tegner rotten på skjermen ved bruk av en SpriteBatch.
     * 
     * @param batch SpriteBatch som brukes for å tegne rotten, sikrer effektiv tegning.
     */
    void render(com.badlogic.gdx.graphics.g2d.SpriteBatch batch);

    
    /** Metode for å håndtere hva som skjer når en rotte blir drept */ 
    void killed();

    /**
     * Sjekker om rotten er fanget eller ikke.
     * 
     * @return true hvis rotten er fanget, ellers false.
     */
    boolean isKilled();

    /**
     * Returnerer rottens gjenværende helsepoeng.
     * 
     * @return Rottens gjenværende helsepoeng.
     */
    int getHealth();


    /**
     * Returnerer rottens posisjon i spillet.
     * 
     * @return En Vector2 som representerer rottens posisjon.
     */
    com.badlogic.gdx.math.Vector2 getPosition();



}
