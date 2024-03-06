package inf112.skeleton.app.view.States;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;

import inf112.skeleton.app.main.SkadedyrMain;
import inf112.skeleton.app.model.SkadedyrModel;

public class MenuState extends State{
    

   // private Texture playButton;
    
    private SkadedyrModel model;

    private BitmapFont titleFont;
    private BitmapFont textFont;
    private Texture mapTexture;


    public MenuState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        if (model == null) {
            System.out.println("Model is null");
        }
       // playButton = new Texture("image.png");
        this.model = model;
        titleFont = new BitmapFont();
        titleFont.getData().setScale(3);
        textFont = new BitmapFont();
        textFont.getData().setScale(5);

        
    }

    @Override
    public void handleInput() {
       if(Gdx.input.isKeyPressed(Keys.SPACE)){
            System.out.println("Enter key pressed");
           gsm.set(new PlayState(gsm, model));
           dispose();
       }
       if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
           Gdx.app.exit();
       }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) { 
    System.out.println("Rendering Menu");

    Gdx.gl.glClearColor(0, 0, 1, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    // Start drawing
    sb.begin();
   

    // Draw the play button
    //sb.draw(playButton, playButton.getWidth() / 2, playButton.getHeight() / 2);
    titleFont.draw(sb, "Press SPACE to play", 300, 300);
    textFont.draw(sb, "Return Of the Rats", 300, 500);



    // End drawing
    sb.end();
        
    }

    @Override
    public void dispose() {
        
        titleFont.dispose();
        textFont.dispose();
        
    }
    

    
}
