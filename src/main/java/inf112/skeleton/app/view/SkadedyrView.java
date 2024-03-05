package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.Cat;
import inf112.skeleton.app.model.entities.Rat;

public class SkadedyrView {
    private SpriteBatch batch;
    private BitmapFont font;
    private Rectangle screenRect = new Rectangle();
    private final SkadedyrModel model;

    private ShapeRenderer shapeRenderer;
    private Texture mapTexture; // Hold the texture to avoid reloading it every frame

    public SkadedyrView(SkadedyrModel model) {
        this.model = model;
    }

    public void create() {
        this.shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.GRAY);

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

	public void draw() {
		ScreenUtils.clear(Color.GREEN);
	
		batch.begin();
		batch.draw(mapTexture, 0, 0); 
		batch.end();
	
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for (Cat cat : model.getCats()) {
			Circle range = cat.getRangeCircle();
			shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.5f);
			shapeRenderer.circle(range.x, range.y, range.radius);
		}
		shapeRenderer.end();
	
		Gdx.gl.glDisable(GL20.GL_BLEND);
	
		batch.begin();
		font.draw(batch, "Velkommen til Skadedyrkontrollørerne", 200, 10);
		font.draw(batch, "Dine liv: " + model.getLives(), 1000, 760);
		font.draw(batch, "Dine penger: " + model.getMoney(), 1000, 840);
		font.draw(batch, "Din Score: " + model.getPoints(), 1000, 800);
		font.draw(batch, "Level: " + model.getLevel(), 1000, 720);
		drawCats();
		drawRats();
		batch.end();
	}

	public void drawCats(){
		for (Cat cat : model.getCats()) {
            Rectangle catRect = cat.getRectangle();
            batch.draw(cat.getTexture(), catRect.x, catRect.y, catRect.width, catRect.height);
        }
	}

	public void drawRats(){
		for (Rat rat : model.getRats()) {
            Rectangle ratRect = rat.getRectangle();
            batch.draw(rat.getTexture(), ratRect.x, ratRect.y, ratRect.width, ratRect.height);
        }
	}

    public void resize(int width, int height) {
        screenRect.width = width;
        screenRect.height = height;
    }
}
