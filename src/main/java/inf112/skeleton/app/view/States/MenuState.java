// package inf112.skeleton.app.view.States;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Input.Keys;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.scenes.scene2d.Actor;
// import com.badlogic.gdx.scenes.scene2d.InputEvent;
// import com.badlogic.gdx.scenes.scene2d.InputListener;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Button;
// import com.badlogic.gdx.scenes.scene2d.ui.Skin;
// import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
// import com.badlogic.gdx.utils.viewport.ScreenViewport;

// import inf112.skeleton.app.model.SkadedyrModel;

// public class MenuState extends State {

//     // private Texture playButton;

//     private SkadedyrModel model;

//     private BitmapFont titleFont;
//     private BitmapFont textFont;
//     private Stage stage;
//     private Button playButton;

//     public MenuState(GameStateManager gsm, SkadedyrModel model) {
//         super(gsm);
//         this.model = model;
//         titleFont = new BitmapFont();
//         titleFont.getData().setScale(3);
//         textFont = new BitmapFont();
//         textFont.getData().setScale(5);

//         stage = new Stage(new ScreenViewport());
//         Gdx.input.setInputProcessor(stage);

//         Skin mySkin = new Skin(Gdx.files.internal("/skin/uiskin.json"));

//         Button playButton = new Button(mySkin, "default");
//         playButton.setPosition(300, 150); // Set the position of the button
//         playButton.setSize(200, 50); // Set the size of the button
//         playButton.addListener(new ChangeListener() {
//             @Override
//             public void changed(ChangeEvent event, Actor actor) {
//                 gsm.set(new PlayState(gsm, model));
//             }
//         });

//         stage.addActor(playButton);

//     }

//     @Override
//     public void handleInput() {
//         // if (Gdx.input.isKeyPressed(Keys.SPACE)) {
//         // System.out.println("Enter key pressed");
//         // gsm.set(new PlayState(gsm, model));
//         // dispose();
//         // }
//         // if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
//         // Gdx.app.exit();
//         // }
//     }

//     @Override
//     public void update(float dt) {
//         handleInput();
//     }

//     @Override
//     public void render(SpriteBatch sb) {
//         System.out.println("Rendering Menu");

//         Gdx.gl.glClearColor(0, 0, 1, 1);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//         // Start drawing
//         sb.begin();

//         // Draw the play button
//         // sb.draw(playButton, playButton.getWidth() / 2, playButton.getHeight() / 2);
//         titleFont.draw(sb, "Press SPACE to play", 300, 300);
//         textFont.draw(sb, "Return Of the Rats", 300, 500);

//         // End drawing
//         sb.end();
//         stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//         stage.draw();

//     }

//     @Override
//     public void dispose() {

//         titleFont.dispose();
//         textFont.dispose();
//         stage.dispose();

//     }

// }
package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import inf112.skeleton.app.controller.SkadedyrController;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.buttons.ButtonFactory;

public class MenuState extends State {

    private SkadedyrModel model;
    private BitmapFont titleFont;
    private BitmapFont textFont;
    private Stage stage;
    private Button playButton;
    private BitmapFont font;
    private SkadedyrController controller;

    public MenuState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;
        titleFont = new BitmapFont();
        titleFont.getData().setScale(3);
        textFont = new BitmapFont();
        textFont.getData().setScale(5);
        this.controller = controller;

        // stage = new Stage(new ScreenViewport());
        // Gdx.input.setInputProcessor(stage);

        // // Create a new pixmap that is filled with a blue color
        // Pixmap pixmap = new Pixmap(300, 50, Pixmap.Format.RGBA8888);
        // pixmap.setColor(Color.CYAN);
        // pixmap.fill();

        // // Create a texture from pixmap
        // Texture buttonTexture = new Texture(pixmap);

        // // Make sure you dispose of the Pixmap and texture when you no longer need
        // them
        // pixmap.dispose();

        // // Create a drawable from the texture
        // TextureRegionDrawable drawable = new TextureRegionDrawable(buttonTexture);

        // // Set the font
        // BitmapFont font = new BitmapFont();

        // // Create the style
        // TextButton.TextButtonStyle textButtonStyle = new
        // TextButton.TextButtonStyle();
        // textButtonStyle.up = drawable; // Set the default drawable
        // textButtonStyle.down = drawable.tint(Color.DARK_GRAY); // Set the drawable
        // for the down state
        // textButtonStyle.font = font;

        // // Create the button
        // playButton = new TextButton("Play", textButtonStyle);
        // playButton.setPosition(300, 150);
        // playButton.setSize(200, 50);
        // playButton.addListener(new ChangeListener() {
        // @Override
        // public void changed(ChangeEvent event, Actor actor) {
        // gsm.set(new PlayState(gsm, model));
        // }
        // });

        // stage.addActor(playButton);
        // }
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont(); // Don't forget to dispose of this later!
        Color upColor = Color.BLACK;
        Color downColor = Color.GREEN;
        Color fontColor = Color.WHITE;

        TextButton playButton = ButtonFactory.createTextButton("Play", upColor, downColor, fontColor, font);
        float x = (stage.getWidth() - playButton.getWidth()) / 2;
        float y = ((stage.getHeight() - playButton.getHeight()) / 2) - 100;
        playButton.setPosition(x, y);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new PlayState(gsm, model));
            }
        });

        ImageButton playImageButton = ButtonFactory.createImageButton("buttonUp.png", "buttonDown.png");
        playImageButton.setPosition((stage.getWidth() - playImageButton.getWidth()) / 2, (stage.getHeight() - playImageButton.getHeight()) / 2);
        playImageButton.setSize(200, 50);
        playImageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                
                gsm.set(new PlayState(gsm, model));
            }
        });

        stage.addActor(playImageButton);

        stage.addActor(playButton);
    }

    @Override
    public void handleInput() {
        // Handle your input here
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
   

        // Clear the screen and set a color
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        // Draw the stage with the actors
        GlyphLayout layout = new GlyphLayout(textFont, "Return Of the Rats"); // Get the width of the text
        float x = (Gdx.graphics.getWidth() - layout.width) / 2; // Calculate the x position
        textFont.draw(sb, "Return Of the Rats", x, 500);
        sb.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        // playButton.dispose();
        titleFont.dispose();
        textFont.dispose();
        textFont.dispose();
        font.dispose();
    }
}
