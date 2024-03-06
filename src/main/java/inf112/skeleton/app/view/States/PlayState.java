package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.main.SkadedyrMain;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.Cat;
import inf112.skeleton.app.model.entities.Rat;
import inf112.skeleton.app.view.SkadedyrView;

public class PlayState extends State{
	private ShapeRenderer shapeRenderer;
	private SkadedyrModel model;
	private BitmapFont font;

	
    protected PlayState(GameStateManager gsm, SkadedyrModel model) {
        super(gsm);
        this.model = model;
		this.shapeRenderer = new ShapeRenderer();
		this.font = new BitmapFont();
	
    }

    @Override
    public void handleInput() {
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			// gsm.set(new MenuState(gsm, model));
            // dispose();
			SkadedyrMain.main(null);
          
       }
        
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
		
		//  // Clear the screen
		//  Gdx.gl.glClearColor(0, 1, 0, 1);
		//  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
		//  // Start drawing
		//  sb.begin();
 
		//  // Draw the map
		//  sb.draw(new Texture(Gdx.files.internal("map.png")), 0, 0);
 
		//  // Draw the cats
		//  for (Cat cat : model.getCats()) {
		// 	 Rectangle catRect = cat.getRectangle();
		// 	 sb.draw(cat.getTexture(), catRect.x, catRect.y, catRect.width, catRect.height);
		//  }
 
		//  // Draw the rats
		//  for (Rat rat : model.getRats()) {
		// 	 Rectangle ratRect = rat.getRectangle();
		// 	 sb.draw(rat.getTexture(), ratRect.x, ratRect.y, ratRect.width, ratRect.height);
		//  }
 
		//  // Draw the text
		//  font.draw(sb, "Velkommen til Skadedyrkontrollørerne", 200, 10);
 
		//  // End drawing
		//  sb.end();
 
		//  // Draw the cat's range circles
		//  for (Cat cat : model.getCats()) {
		// 	 Circle range = cat.getRangeCircle();
		// 	 shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		// 	 shapeRenderer.setColor(0, 0, 1, 1); // Set the color to blue
		// 	 shapeRenderer.circle(range.x, range.y, range.radius);
		// 	 shapeRenderer.end();
		//  }
		// CatMenu catMenu = new CatMenu();
        
        ScreenUtils.clear(Color.GREEN);
        
        batch.begin();
       // catMenu.draw(batch);
        batch.draw(SkadedyrView.mapTexture, 0, 0); // Use the preloaded texture
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Cat cat : model.getCats()) {
            Circle range = cat.getRangeCircle();
			shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.5f);
            shapeRenderer.circle(range.x, range.y, range.radius);
        }
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

        //catMenu.draw(shapeRenderer) ;

        batch.begin();
        for (Cat cat : model.getCats()) {
            Rectangle catRect = cat.getRectangle();
            batch.draw(cat.getTexture(), catRect.x, catRect.y, catRect.width, catRect.height);
        }
        for (Rat rat : model.getRats()) {
            Rectangle ratRect = rat.getRectangle();
            batch.draw(rat.getTexture(), ratRect.x, ratRect.y, ratRect.width, ratRect.height);
        }
        font.draw(batch, "Velkommen til Skadedyrkontrollørerne", 200, 10);
        font.draw(batch, "Dine liv: " + model.getLives(), 1000, 760);
        font.draw(batch, "Dine penger: " + model.getMoney(), 1000, 840);
        font.draw(batch, "Din Score: " + model.getPoints(), 1000, 800);
        font.draw(batch, "Level: " + model.getLevel(), 1000, 720);
        drawCats(batch);
        drawRats(batch);
        batch.end();
		 
	 }

	 public void drawCats(SpriteBatch batch){
		for (Cat cat : model.getCats()) {
            Rectangle catRect = cat.getRectangle();
            batch.draw(cat.getTexture(), catRect.x, catRect.y, catRect.width, catRect.height);
        }
	}

	public void drawRats(SpriteBatch batch){
		for (Rat rat : model.getRats()) {
            Rectangle ratRect = rat.getRectangle();
			batch.draw(rat.getTexture(), ratRect.x, ratRect.y, ratRect.width, ratRect.height);
        }
	}


 

    @Override
    public void dispose() {
		shapeRenderer.dispose();
		font.dispose();
		
		
		
    }



    
}
