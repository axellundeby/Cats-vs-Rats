package inf112.skeleton.app.model.catmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.view.SkadedyrView;

import java.util.ArrayList;
import java.util.HashMap;

public class CatMenu {

    private HashMap<Cat, Rectangle> catsMap;
    private ArrayList<Cat> availableCatsList;
    private Rectangle menuRect = new Rectangle(832, 0, SkadedyrView.screenRect.x - 800, SkadedyrView.screenRect.y);
    private static final int CATDIM = 150;
    private static final int MARGIN = 30;
    private Cat selected;
    private Texture coinSackTexture;


    public CatMenu() {
        this.catsMap = new HashMap<>();
        this.availableCatsList = new ArrayList<>();

    }
    
    public void init(){
        this.coinSackTexture = new Texture(Gdx.files.internal("noMoney.png"));
        availableCatsList.add(new BasicCat());
        availableCatsList.add(new FreezeCat());
        availableCatsList.add(new ShotgunCat());

        int i = 0;
        for (Cat cat : availableCatsList) {
            catsMap.put(cat, new Rectangle(getX() + MARGIN, getY() + MARGIN + i * CATDIM, CATDIM, CATDIM));
            i++;
        }
        selected = availableCatsList.get(0);
    }

    public void draw(SpriteBatch batch, int playerMoney) {
        for (Cat cat : availableCatsList) {
            Rectangle crt = catsMap.get(cat);
            batch.draw(cat.getTexture(), crt.x, crt.y, crt.width, crt.height);
            if (playerMoney < cat.getCost()) {
                float halfWidth = crt.width / 2;
                float halfHeight = crt.height / 2;
                float centerX = crt.x + halfWidth / 2;
                float centerY = crt.y + halfHeight / 2;
                batch.draw(coinSackTexture, centerX, centerY, halfWidth, halfHeight);
            }
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        Vector2 center = catsMap.get(selected).getCenter(new Vector2());
        shapeRenderer.circle(center.x, center.y, CATDIM/2);
    }
    
    public void selector(Vector2 pos){
        for (Cat cat : availableCatsList) {
            if (catsMap.get(cat).contains(pos))
                selected = cat;
        }
    }

    public Cat getSelectedCat(){
        return selected;
    }

    private float getX() {
        return menuRect.x;
    }

    private float getY() {
        return menuRect.y;
    }
}
