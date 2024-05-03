package inf112.skeleton.app.view.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    /**
     * Renders the current state using the provided SpriteBatch.
     *
     * @param sb The SpriteBatch object used for drawing.
     */
    public abstract void render(SpriteBatch sb);

    /**
     * Cleans up resources used by this state.
     * This method should be called when a state is no longer needed to free up memory and prevent leaks.
     */
    public abstract void dispose();
    


}
