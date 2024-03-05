package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.Cat;
import inf112.skeleton.app.model.entities.Rat;

public class SkadedyrView {
	private SpriteBatch batch;
	private BitmapFont font;
	// private Sound bellSound;
	private Rectangle screenRect = new Rectangle();
	private final SkadedyrModel model;

	private ShapeRenderer shapeRenderer;

	public SkadedyrView(SkadedyrModel model) {
		this.model = model;
	}

	public void create() {
		// Called at startup
		this.shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);

		// bellSound = Gdx.audio.newSound(Gdx.files.internal("INSERT PATH TO SOUND"));
		Gdx.graphics.setForegroundFPS(60);
	}

	public void dispose() {
		// Graphics and sound resources aren't managed by Java's garbage collector, so
		// they must generally be disposed of manually when no longer needed. But,
		// any remaining resources are typically cleaned up automatically when the
		// application exits, so these aren't strictly necessary here.
		// (We might need to do something like this when loading a new game level in
		// a large game, for instance, or if the user switches to another application
		// temporarily (e.g., incoming phone call on a phone, or something).

		batch.dispose();
		font.dispose();
		for (Cat cat : model.getCats()) {
			cat.getTexture().dispose();

		}
		for (Rat rat : model.getRats()) {
			rat.getTexture().dispose();

		}
		shapeRenderer.dispose();
		// bellSound.dispose();
	}

	public void draw() {
		CatMenu catMenu = new CatMenu(this);

		// Start with a blank screen
		ScreenUtils.clear(Color.GREEN);

		// Draw calls should be wrapped in batch.begin() ... batch.end()
		batch.begin();

		batch.draw(new Texture(Gdx.files.internal("map.png")), 0, 0);
		batch.end();

		//
		// Gdx.gl.glClearColor(1, 1, 1, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//

		catMenu.draw(shapeRenderer);
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

		shapeRenderer.setColor(0, 0, 1, 1); // Set the color to blue
		for (Cat cat : model.getCats()) {
			// Draw cat's range circle
			Circle range = cat.getRangeCircle();
			// Draw the circle using the position and radius from the Circle object
			shapeRenderer.circle(range.x, range.y, range.radius);
			
		}
		shapeRenderer.end();

		batch.begin();
		catMenu.draw(batch);
		for (Cat cat : model.getCats()) {
			Rectangle catRect = cat.getRectangle();
			batch.draw(cat.getTexture(), catRect.x, catRect.y, catRect.width, catRect.height);

		}
		for (Rat rat : model.getRats()) {
			Rectangle ratRect = rat.getRectangle();
			batch.draw(rat.getTexture(), ratRect.x, ratRect.y, ratRect.width, ratRect.height);
			// System.out.println("Rat at " + ratRect.x + " " + ratRect.y);
		}
		font.draw(batch, "Velkommen til Skadedyrkontrollørerne", 100, screenRect.height - 20);
		batch.end();
	}

	public float getScreenWidth() {
		return screenRect.width;
	}

	public float getScreenHeight() {
		return screenRect.height;
	}

	public void resize(int width, int height) {
		screenRect.width = width;
		screenRect.height = height;
	}
}