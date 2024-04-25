
package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;

public class GameOverState extends State {
    private SkadedyrModel model;
    private Stage stage;
    private Texture gameOverImage;
    private float alpha = 0f;

    public GameOverState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;

        this.stage = new Stage();
        this.gameOverImage = new Texture("Spill_GameOver.jpg");

        Gdx.input.setInputProcessor(stage);
        setupPlayButtons();
    }

    private void setupPlayButtons() {
        ImageButton playImageButton = ButtonFactory.createImageButton("buttons_game/Spill_Play.png",
                "buttons_game/Spill_Play-Down.png");

        playImageButton.setSize(200, 200);
        playImageButton.setPosition((stage.getWidth() - playImageButton.getWidth()) / 2,
                (stage.getHeight() - playImageButton.getHeight()) / 2 - 200);

        playImageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // model.setLevel(0);
                model.restart();
                gsm.set(new MenuState(gsm, model));
            }
        });

        stage.addActor(playImageButton);
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

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        gameOverImage.dispose();
    }
}
