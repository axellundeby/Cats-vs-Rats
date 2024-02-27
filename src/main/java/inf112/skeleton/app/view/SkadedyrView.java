package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.model.SkadedyrModel;


public class SkadedyrView {
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture spriteImage;
	// private Sound bellSound;
	private Rectangle spriteRect;
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
		spriteImage = new Texture(Gdx.files.internal("rat1.png"));
		spriteRect = new Rectangle(1, 1, spriteImage.getWidth() / 10, spriteImage.getHeight() / 10);
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
		spriteImage.dispose();
		// bellSound.dispose();
	}

	public void draw(){
		// Start with a blank screen
		ScreenUtils.clear(Color.GREEN);

		// Draw calls should be wrapped in batch.begin() ... batch.end()
		batch.begin();
		batch.draw(new Texture(Gdx.files.internal("map.png")), 0, 0);
		batch.draw(spriteImage, spriteRect.x, spriteRect.y, spriteRect.width, spriteRect.height);
		font.draw(batch, "Velkommen til Skadedyrkontrollørerne", 200, 10);
		batch.end();
	}

	public void resize(int width, int height){
		screenRect.width = width;
		screenRect.height = height;
	}
}