package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.controller.buttons.menu.MenuButtons;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.GameResourceFactory;

public class MenuState extends State {
    private SkadedyrModel model;
    private Stage stage;
    private Texture background;
    private final GameStateManager gsm;
    private MenuButtons menu;
    private GameResourceFactory resourceFactory;

    public MenuState(GameStateManager gsm, SkadedyrModel model, GameResourceFactory resourceFactory) {
        super(gsm);
        this.model = model;
        this.gsm = gsm;
        this.resourceFactory = resourceFactory;
        this.stage = new Stage();
        this.background = new Texture("Spill_Forside.jpg");

        Gdx.input.setInputProcessor(stage);

        menu = new MenuButtons(model, resourceFactory);
        stage.addActor(menu.helpButton());
        stage.addActor(menu.playButton());
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        stage.draw();
        if (model.getHelp()) {
            model.resetStartGame();
            gsm.set(new HelpState(gsm, model, resourceFactory));
        } 
        if (model.getStartGame()) {
            model.resetStartGame();
            gsm.set(new PlayState(gsm, model, resourceFactory));
        }

    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
