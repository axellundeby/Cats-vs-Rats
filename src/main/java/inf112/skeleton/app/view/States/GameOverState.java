package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameOverState extends State {

    private BitmapFont titleFont;
    private BitmapFont textFont;
    // private SkadedyrModel model;
    private ShapeRenderer shapeRenderer;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        // this.model = model;
        titleFont = new BitmapFont();
        titleFont.getData().setScale(3);
        textFont = new BitmapFont();
        textFont.getData().setScale(5);
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.2f)); // Semi-transparent black
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        sb.begin();
        GlyphLayout layout = new GlyphLayout(titleFont, "GAME OVER"); // Get the width/height of the text
        float x = (Gdx.graphics.getWidth() - layout.width) / 2; // Calculate the x position
        float y = (Gdx.graphics.getHeight() + layout.height) / 2; // Calculate the y position
        titleFont.draw(sb, layout, x, y);
        sb.end();
    }

    @Override
    public void dispose() {

        titleFont.dispose();
        textFont.dispose();
        shapeRenderer.dispose();

    }

}
