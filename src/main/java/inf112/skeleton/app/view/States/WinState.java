package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class WinState extends State {
    private Texture winImage;
    private float alpha = 0f; // Starting alpha at 0 for fully transparent
    private final float FADE_IN_SPEED = 0.01f;
    private BitmapFont titleFont;

    public WinState(GameStateManager gsm) {
        super(gsm);
        winImage = new Texture("claw.png");
        titleFont = new BitmapFont();
        
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1.0f, 0.7529f, 0.7961f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        
        // Ensure the alpha doesn't exceed 1
        if (alpha < 1f) {
            alpha += FADE_IN_SPEED;
            alpha = Math.min(alpha, 1f); // Ensures alpha does not exceed 1
        }

        sb.setColor(1, 1, 1, alpha); // Set the alpha for drawing
        sb.draw(winImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setColor(1, 1, 1, 1); // Reset the color to full opacity
        GlyphLayout layout = new GlyphLayout(titleFont, "You Win!");
        float x = (Gdx.graphics.getWidth() - layout.width) / 2; 
        float y = (Gdx.graphics.getHeight() + layout.height) / 2; 
        titleFont.draw(sb, layout, x, y);
        
        sb.end();
    }

    @Override
    public void dispose() {
        winImage.dispose();
    }
}
