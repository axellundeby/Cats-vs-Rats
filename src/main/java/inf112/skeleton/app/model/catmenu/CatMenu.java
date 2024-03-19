package inf112.skeleton.app.model.catmenu;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.entities.cat.AngryCat;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.view.SkadedyrView;

public class CatMenu {

    private HashMap<Cat, Rectangle> catsMap;
    private ArrayList<Cat> availableCatsList;
    private Rectangle menuRect = new Rectangle(832, 0, SkadedyrView.screenRect.x - 800, SkadedyrView.screenRect.y);
    private static final int CATDIM = 150;
    private static final int MARGIN = 30;
    private Cat selected;
    // private SkadedyrView skadedyrView;

    public CatMenu() {
        // this.skadedyrView = skadedyrView;
        // menuRect.x = 832;
        // menuRect.y = 0;
        // menuRect.width = SkadedyrView.screenRect.x - 800;
        // menuRect.height = SkadedyrView.screenRect.y;
        this.catsMap = new HashMap<>();
        this.availableCatsList = new ArrayList<>();

    }
    public void init(){
        availableCatsList.add(new BasicCat());
        availableCatsList.add(new AngryCat());
        availableCatsList.add(new FreezeCat());
        availableCatsList.add(new ShotgunCat());

        int i = 0;
        for (Cat cat : availableCatsList) {
            catsMap.put(cat, new Rectangle(getX() + MARGIN, MARGIN + i *CATDIM, CATDIM, CATDIM));
            // cat.setSize(CATDIM);
            // cat.setPos((int) getX() + MARGIN, MARGIN + i * CATDIM);
            i++;
        }
        selected = availableCatsList.get(0);
    }

    public Rectangle getMenuRectangle() {
        return menuRect;
    }

    public void draw(SpriteBatch batch) {
        for (Cat cat : availableCatsList) {
            Rectangle crt = catsMap.get(cat);
            batch.draw(cat.getTexture(), crt.x, crt.y, crt.width, crt.height);
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        //     shapeRenderer.rect(getX(), getY(), getW(), getH());
        shapeRenderer.setColor(Color.WHITE);
        // Rectangle selectedRect = catsMap.get(selected);
        // shapeRenderer.rect(selectedRect.x, selectedRect.y, selectedRect.width, selectedRect.height);
        Vector2 center = catsMap.get(selected).getCenter(new Vector2());
        shapeRenderer.circle(center.x, center.y, CATDIM/2);
    }
    

    public Sprite getSprite(int index) {
        return availableCatsList.get(index).getSprite();
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

    private float getH() {
        return menuRect.height;
    }

    private float getW() {
        return menuRect.width;
    }

}
