package inf112.skeleton.app.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.controller.SkadedyrController;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.view.States.GameStateManager;
import inf112.skeleton.app.view.States.MenuState;

public class SkadedyrView {
    private SpriteBatch batch;
    private BitmapFont font;
    // private Sound bellSound;
    public static Rectangle screenRect = new Rectangle();
    private final SkadedyrModel model;
    private GameStateManager gsm;

    private ShapeRenderer shapeRenderer;
    public static Texture mapTexture; // Hold the texture to avoid reloading it every frame
    
   

    public SkadedyrView(SkadedyrModel model) {
        this.model = model;
     
        

    }

    public void create() {
        // Called at startup
        this.shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        gsm = new GameStateManager();
        GameStateManager.set(new MenuState(gsm, model));
        mapTexture = new Texture(Gdx.files.internal("map.png")); // Load once in create
        Gdx.graphics.setForegroundFPS(60);
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        mapTexture.dispose(); // Dispose the map texture
        for (Cat cat : model.getCats()) {
            cat.getTexture().dispose();
        }
        for (Rat rat : model.getRats()) {
            rat.getTexture().dispose();
        }
        shapeRenderer.dispose();
    }

    public float getScreenWidth() {
        return screenRect.width;
    }

    public float getScreenHeight() {
        return screenRect.height;
    }

    public ArrayList<Cat> getCats(){
        return model.getCats();
    }

    public void render() {
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    public void resize(int width, int height) {
        screenRect.width = width;
        screenRect.height = height;
    }
}
