package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;

public class MenuState extends State {
    private SkadedyrModel model;
    private Stage stage;

    public MenuState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        setupPlayButtons();
    }

    private void setupPlayButtons() {
        ImageButton playImageButton = ButtonFactory.createImageButton("buttons_game/Spill_Play.png", "buttons_game/Spill_Play-Down.png");
        
        playImageButton.setSize(200, 200);
        playImageButton.setPosition((stage.getWidth() - playImageButton.getWidth()) / 2, (stage.getHeight() - playImageButton.getHeight()) / 2 - 100);

      
        playImageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStateManager.set(new PlayState(gsm, model));
            }
        });

        stage.addActor(playImageButton);
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
