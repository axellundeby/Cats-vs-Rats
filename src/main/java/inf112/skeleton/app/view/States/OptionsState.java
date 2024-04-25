package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.model.SkadedyrModel;

public class OptionsState extends State {
    private Texture helpImage;

    protected OptionsState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        helpImage = new Texture("Spill_Help.png");
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(helpImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        helpImage.dispose();
    }
}