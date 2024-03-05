package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.Cat;
import inf112.skeleton.app.model.entities.Rat;
import inf112.skeleton.app.view.States.GameStateManager;
import inf112.skeleton.app.view.States.MenuState;

public class SkadedyrView {
	private SpriteBatch batch;
	private BitmapFont font;
	// private Sound bellSound;
	private Rectangle screenRect = new Rectangle();
	private final SkadedyrModel model;
	private GameStateManager gsm;

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
		gsm = new GameStateManager();
		gsm.set(new MenuState(gsm, model));
		System.out.println("View created");


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
	public float getScreenWidth() {
		return screenRect.width;
	}

	public float getScreenHeight() {
		return screenRect.height;
	}

	
	public void render() {
		System.out.println("Rendering in MainView");
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
		

		
		
	

	public void resize(int width, int height) {
		screenRect.width = width;
		screenRect.height = height;
	}
}