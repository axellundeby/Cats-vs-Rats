package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import inf112.skeleton.app.controller.buttons.menu.MenuButtons;
import inf112.skeleton.app.model.SkadedyrModel;

public class HelpState extends State {

    private SkadedyrModel model;
    private Stage stage;
    private Texture help;
    private float alpha = 0f;
    private MenuButtons menu;

    public HelpState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;
        this.stage = new Stage();
        this.help = new Texture("Spill_Help.png");
        menu = new MenuButtons(model, stage);
        stage.addActor(menu.playButton());

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        if (alpha < 1f) {
            alpha += 0.01f;
            alpha = Math.min(alpha, 1f);
        } else {
            if (alpha > 0f) {
                alpha -= 0.01f;
                alpha = Math.max(alpha, 0f);
            }
        }

        sb.setColor(1, 1, 1, alpha);
        sb.draw(help, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setColor(1, 1, 1, alpha);

        sb.end();

        if (model.getStartGame()) {
            gsm.set(new PlayState(gsm, model));
        }
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        help.dispose();
    }

}
