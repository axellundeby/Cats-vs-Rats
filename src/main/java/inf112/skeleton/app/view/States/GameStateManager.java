package inf112.skeleton.app.view.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {
    private State currentState;

    public void set(State newState) {
        if (currentState != null) {
            currentState.dispose();
        }
        currentState = newState;
    }

    public void update(float dt) {
        if (currentState != null) {
            currentState.update(dt);
        }
    }

    public void render(SpriteBatch sb) {
        if (currentState != null) {
            currentState.render(sb);
        }
    }

    public void dispose() {
        if (currentState != null) {
            currentState.dispose();
        }
    }
}
