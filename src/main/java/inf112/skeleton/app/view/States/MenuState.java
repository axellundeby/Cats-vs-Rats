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

public class MenuState extends State {
    private SkadedyrModel model;
    private Stage stage;
    private Texture background;
    private final static int BUTTON_HEIGHT=50; 

    public MenuState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;

        this.stage = new Stage();
        this.background = new Texture("Spill_Forside.jpg");

        Gdx.input.setInputProcessor(stage);
        setupPlayButtons();
        setupHelpButtons();
    }

    private void setupPlayButtons() {
        ImageButton playImageButton = ButtonFactory.createImageButton("buttons_game/Spill_Play.png",
                "buttons_game/Spill_Play-Down.png");

        playImageButton.setSize(200, 100);
        playImageButton.setPosition((stage.getWidth() - playImageButton.getWidth()) / 2 - 200 , BUTTON_HEIGHT);
                // (stage.getHeight() - playImageButton.getHeight()) / 2 - 100);

        playImageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStateManager.set(new PlayState(gsm, model));
            }
        });

        stage.addActor(playImageButton);
    }

    private void setupHelpButtons() {
        ImageButton playImageButton = ButtonFactory.createImageButton("buttons_game/Spill_Help.png",
                "buttons_game/Spill_Play-Down.png");

        playImageButton.setSize(200, 150);
        playImageButton.setPosition((stage.getWidth() - playImageButton.getWidth()) / 2 + 200 , BUTTON_HEIGHT-20);
                // (stage.getHeight() - playImageButton.getHeight()) / 2 - 100);

        playImageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStateManager.set(new OptionsState(gsm, model));
            }
        });

        stage.addActor(playImageButton);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
