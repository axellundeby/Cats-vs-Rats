// package inf112.skeleton.app.view.States;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.GlyphLayout;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.scenes.scene2d.Actor;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

// import inf112.skeleton.app.controller.buttons.ButtonFactory;
// import inf112.skeleton.app.model.SkadedyrModel;

// public class WinState extends State {
//     private Texture winImage;
//     private float alpha = 0f; // Starting alpha at 0 for fully transparent
//     private final float FADE_IN_SPEED = 0.01f;
//     private BitmapFont titleFont;
//     private Stage stage;
//     private SkadedyrModel model;

//     public WinState(GameStateManager gsm, SkadedyrModel model) {
//         super(gsm);
//         this.model = model;

//         this.stage = new Stage();
//         this.winImage = new Texture("buttons_game/gutta.png");

//         Gdx.input.setInputProcessor(stage);
//         setupPlayButtons();

//     }

//     private void setupPlayButtons() {
//         ImageButton playImageButton = ButtonFactory.createImageButton("buttons_game/Spill_Play.png",
//                 "buttons_game/Spill_Play-Down.png");

//         playImageButton.setSize(200, 200);
//         playImageButton.setPosition((stage.getWidth() - playImageButton.getWidth()) / 2,
//                 (stage.getHeight() - playImageButton.getHeight()) / 2 - 100);

//         playImageButton.addListener(new ChangeListener() {
//             @Override
//             public void changed(ChangeEvent event, Actor actor) {
//                 GameStateManager.set(new MenuState(gsm, model));
//             }
//         });

//         stage.addActor(playImageButton);
//     }

//     @Override
//     public void render(SpriteBatch sb) {

//         Gdx.gl.glClearColor(1.0f, 0.7529f, 0.7961f, 1.0f);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//         sb.begin();

//         if (alpha < 1f) {
//             alpha += FADE_IN_SPEED;
//             alpha = Math.min(alpha, 1f);
//         }

//         sb.setColor(1, 1, 1, alpha); 
//         sb.draw(winImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//         sb.setColor(1, 1, 1, 1); 
//         GlyphLayout layout = new GlyphLayout(titleFont, "You Win!");
//         float x = (Gdx.graphics.getWidth() - layout.width) / 2; 
//         float y = (Gdx.graphics.getHeight() + layout.height) / 2; 
//         titleFont.draw(sb, layout, x, y);

//         sb.end();

//         stage.draw();
//     }

//     @Override
//     public void dispose() {
//         winImage.dispose();
//     }
// }

package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import inf112.skeleton.app.controller.buttons.ButtonFactory;
import inf112.skeleton.app.model.SkadedyrModel;

public class WinState extends State {
    private SkadedyrModel model;
    private Stage stage;
    private Texture winImage;
    private float alpha = 0f; // Starting alpha at 0 for fully transparent
    private final float FADE_IN_SPEED = 0.01f;
    private BitmapFont titleFont;

    public WinState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;

        this.stage = new Stage();
        this.winImage = new Texture("claw.png");
        this.titleFont = new BitmapFont();
       

        Gdx.input.setInputProcessor(stage);
        setupPlayButtons();
    }

    private void setupPlayButtons() {
        ImageButton playImageButton = ButtonFactory.createImageButton("buttons_game/Spill_Play.png",
                "buttons_game/Spill_Play-Down.png");

        playImageButton.setSize(200, 200);
        playImageButton.setPosition((stage.getWidth() - playImageButton.getWidth()) / 2,
                (stage.getHeight() - playImageButton.getHeight()) / 2 - 100);

        playImageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                model.setLevel(0);
                model.restart();
                GameStateManager.set(new MenuState(gsm, model));
            }
        });

        stage.addActor(playImageButton);
    }

    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClearColor(1.0f, 0.7529f, 0.7961f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();

        if (alpha < 1f) {
            alpha += FADE_IN_SPEED;
            alpha = Math.min(alpha, 1f);
        }

        sb.setColor(1, 1, 1, alpha);
        sb.draw(winImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setColor(1, 1, 1, 1);
        GlyphLayout layout = new GlyphLayout(titleFont, "You Win!");
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() + layout.height) / 2;
        titleFont.draw(sb, layout, x, y);

        sb.end();

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        winImage.dispose();
    }
}
