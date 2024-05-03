package inf112.skeleton.app.view.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {
    private State currentState;

    /**
     * Called when the application window is resized.
     * Updates the screen rectangle's width and height.
     * @param width The new width.
     * @param height The new height.
     */
    public void set(State newState) {
        if (currentState != null) {
            currentState.dispose();
        }
        currentState = newState;
    }

    /**
     * Gets the current game state.
     * @return The current game state.
     */
    public State getState(){
        return currentState;
    }

     /**
     * Renders the current game state using the given sprite batch.
     * If there is no current game state, this method does nothing.
     * @param sb The sprite batch to use for rendering.
     */
    public void render(SpriteBatch sb) {
        if (currentState != null) {
            currentState.render(sb);
        }
    }
}
