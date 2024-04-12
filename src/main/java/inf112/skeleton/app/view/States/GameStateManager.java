package inf112.skeleton.app.view.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {
    private static State currentState;

    /**
     * Called when the application window is resized.
     * Updates the screen rectangle's width and height.
     * @param width The new width.
     * @param height The new height.
     */
    public static void set(State newState) {
        if (currentState != null) {
            currentState.dispose();
        }
        currentState = newState;
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
