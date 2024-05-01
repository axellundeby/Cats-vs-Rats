package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.ICat;
import inf112.skeleton.app.model.entities.rat.IRat;
import inf112.skeleton.app.view.States.GameStateManager;
import inf112.skeleton.app.view.States.MenuState;

public class SkadedyrView {
    private SpriteBatch batch;
    private BitmapFont font;
    public static Rectangle screenRect = new Rectangle();
    private final SkadedyrModel model;
    private GameStateManager gsm;
    private ShapeRenderer shapeRenderer;

    public SkadedyrView(SkadedyrModel model) {
        this.model = model;
    }

    /**
     * Called when the application is created.
     * Initializes the shape renderer, sprite batch, font, and game state manager.
     */
    public void create() {
        // Called at startup
        this.shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        gsm = new GameStateManager();
        gsm.set(new MenuState(gsm, model));

        Gdx.graphics.setForegroundFPS(60);
    }
    

    /**
     * Disposes of all resources used by the SkadedyrView.
     * This includes the sprite batch, font, and textures used by the cats and rats.
     */
    public void dispose() {
        batch.dispose();
        font.dispose();

        for (ICat cat : model.getCats()) {
            cat.getTexture().dispose();
        }
        for (IRat rat : model.getRats()) {
            rat.getTexture().dispose();
        }
        shapeRenderer.dispose();
    }

    /**
     * Renders the current game state.
     */
    public void render() {
        gsm.render(batch);
        model.setState(gsm.getState());
    }

    /**
     * Called when the application window is resized.
     * Updates the screen rectangle's width and height.
     * 
     * @param width  The new width.
     * @param height The new height.
     */
    public void resize(int width, int height) {
        screenRect.width = width;
        screenRect.height = height;
    }
}
