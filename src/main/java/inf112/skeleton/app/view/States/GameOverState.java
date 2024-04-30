
package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.controller.buttons.menu.MenuButtons;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.GameResourceFactory;

public class GameOverState extends State {
    private SkadedyrModel model;
    private Stage stage;
    private Texture gameOverImage;
    private float alpha = 0f;
    private MenuButtons menu;
    private GameResourceFactory resourceFactory;

    public GameOverState(GameStateManager gsm, SkadedyrModel model, GameResourceFactory resourceFactory) {
        super(gsm);
        this.model = model;
        this.resourceFactory = resourceFactory;

        this.stage = new Stage();
        this.gameOverImage = new Texture("Spill_GameOver.jpg");

        menu = new MenuButtons(model, resourceFactory);
        stage.addActor(menu.exitButton());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        if (alpha < 1f) {
            alpha += 0.01f;
            alpha = Math.min(alpha, 1f);
        }
        sb.setColor(1f, 1f, 1f, alpha);
        sb.draw(gameOverImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setColor(1f, 1f, 1f, 1f);
        sb.end();

        

        

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        gameOverImage.dispose();
    }
}
