package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import inf112.skeleton.app.main.SkadedyrMain;
import inf112.skeleton.app.model.SkadedyrModel;

public  class GameOverState extends State{

    private BitmapFont titleFont;
    private BitmapFont textFont;
    //private SkadedyrModel model;
    private ShapeRenderer shapeRenderer;


    public GameOverState(GameStateManager gsm) {
        super(gsm);
      //  this.model = model;
        titleFont = new BitmapFont();
        titleFont.getData().setScale(3);
        textFont = new BitmapFont();
        textFont.getData().setScale(5);
        shapeRenderer = new ShapeRenderer();

        
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
            SkadedyrMain.main(null);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.2f));  // Semi-transparent black
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        // Draw the "GAME OVER" text
        sb.begin();
        titleFont.draw(sb, "GAME OVER", (Gdx.graphics.getWidth() / 2), Gdx.graphics.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {

        titleFont.dispose();
        textFont.dispose();
        shapeRenderer.dispose();

        
    }
    
}
