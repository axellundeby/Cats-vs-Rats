package inf112.skeleton.app.model.catmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.cat.ICat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.SkadedyrView;
import java.util.ArrayList;
import java.util.HashMap;

public class CatMenu implements ICatMenu{

    private HashMap<ICat, Rectangle> catsMap;
    private ArrayList<ICat> availableCatsList;
    private Rectangle menuRect = new Rectangle(0, 0, SkadedyrView.screenRect.x - 800, SkadedyrView.screenRect.y);
    private static final int CATDIM = 150;
    private static final int MARGIN_Y = 50;
    private static final int MARGIN_X = 30;
    private ICat selected;
    private Texture coinSackTexture;
    private GameResourceFactory resourceFactory;

    public CatMenu(GameResourceFactory resourceFactory) {
        this.catsMap = new HashMap<>();
        this.availableCatsList = new ArrayList<>();
        this.resourceFactory = resourceFactory;
    }
    
    @Override
    public void init(){
        this.coinSackTexture = new Texture(Gdx.files.internal("buttons_game/noMoney.png"));
        availableCatsList.add(new BasicCat(resourceFactory));
        availableCatsList.add(new FreezeCat(resourceFactory));
        availableCatsList.add(new ShotgunCat(resourceFactory));

        int i = 0;
        for (ICat cat : availableCatsList) {
            catsMap.put(cat, new Rectangle(getX() + MARGIN_X + i * CATDIM, getY() + MARGIN_Y, CATDIM, CATDIM));
            i++;
        }
        selected = null;
    }

    
    @Override
    public void draw(SpriteBatch batch, int playerMoney) {
        for (ICat cat : availableCatsList) {
            Rectangle crt = catsMap.get(cat);
            batch.draw(cat.getMenuTexture(), crt.x, crt.y, crt.width, crt.height);
            if (playerMoney < cat.getCost()) {
                float halfWidth = crt.width / 2;
                float halfHeight = crt.height / 2;
                float centerX = crt.x + halfWidth / 2;
                float centerY = crt.y + halfHeight / 2;
                batch.draw(coinSackTexture, centerX, centerY, halfWidth, halfHeight);
            }
        }
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        if (selected != null){
            shapeRenderer.setColor(Color.WHITE);
            Vector2 center = catsMap.get(selected).getCenter(new Vector2());
            shapeRenderer.circle(center.x, center.y, CATDIM/2);
        }
    }
    
    @Override
    public void selector(Vector2 pos){
        for (ICat cat : availableCatsList) {
            if (catsMap.get(cat).contains(pos)){
                selected = cat;
                return;
            }
        }
        selected = null;
    }

    @Override
    public void deselect(){
        selected = null;
    }

    @Override
    public ICat getSelectedCat(){
        return selected;
    }

    @Override
    public float getX() {
        return menuRect.x;
    }

    @Override
    public float getY() {
        return menuRect.y;
    }
}
