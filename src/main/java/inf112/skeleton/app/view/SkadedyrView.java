package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.model.Cat;
import inf112.skeleton.app.model.Rat;
import inf112.skeleton.app.model.SkadedyrModel;


public class SkadedyrView {
	private SpriteBatch batch;
	private BitmapFont font;
	// private Sound bellSound;
	private Rectangle screenRect = new Rectangle();
	private final SkadedyrModel model;
	

	public SkadedyrView(SkadedyrModel model){
		this.model = model;
	}

	public void create() {
		// Called at startup


		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);

		// bellSound = Gdx.audio.newSound(Gdx.files.internal("INSERT PATH TO SOUND"));
		Gdx.graphics.setForegroundFPS(60);
	}

	public void dispose(){
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
		// bellSound.dispose();
	}

	public void draw(){
		// Start with a blank screen
		ScreenUtils.clear(Color.GREEN);

		

		// Draw calls should be wrapped in batch.begin() ... batch.end()
		batch.begin();
		batch.draw(new Texture(Gdx.files.internal("map.png")), 0, 0);
		for (Cat cat : model.getCats()) {
			Rectangle catRect = cat.getRectangle();
			batch.draw(cat.getTexture(), catRect.x, catRect.y, catRect.width, catRect.height);
			
		}
		for (Rat rat : model.getRats()) {
			Rectangle ratRect = rat.getRectangle();
			batch.draw(rat.getTexture(), ratRect.x, ratRect.y, ratRect.width, ratRect.height);
			
		}
		font.draw(batch, "Velkommen til Skadedyrkontrollørerne", 200, 10);
		batch.end();
	}

	public void resize(int width, int height){
		screenRect.width = width;
		screenRect.height = height;
	}
}