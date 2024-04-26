
package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.controller.buttons.menu.MenuButtons;
import inf112.skeleton.app.model.SkadedyrModel;

public class GameOverState extends State {
    private SkadedyrModel model;
    private Stage stage;
    private Texture gameOverImage;
    private float alpha = 0f;
    private MenuButtons menu;

    public GameOverState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;

        this.stage = new Stage();
        this.gameOverImage = new Texture("Spill_GameOver.jpg");

        Gdx.input.setInputProcessor(stage);
        menu = new MenuButtons(model, stage);
        stage.addActor(menu.helpButtonPlay());
        stage.addActor(menu.playButton());
     ;
    }

    

    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();

        if (alpha < 1f) {
            alpha += 0.009f;
            alpha = Math.min(alpha, 1f);
        }
        sb.setColor(1, 1, 1, alpha);
        sb.draw(gameOverImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setColor(1f, 1f, 1f, 1f);
        sb.end();

        // if (model.getStartGame()) {
        //     gsm.set(new PlayState(gsm, model));
        // }
        if (model.getHelp()) {
            gsm.set(new HelpState(gsm, model));
        }
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        gameOverImage.dispose();
    }
}
