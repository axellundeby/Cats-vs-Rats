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

import inf112.skeleton.app.view.SkadedyrView;

public class CatMenu {

    private HashMap<Texture, Rectangle> availableCatsMap;
    private ArrayList<Sprite> availableCatsList;
    private Rectangle menuRect = new Rectangle();
    private static final int CATDIM = 150;
    private static final int MARGIN = 30;
    private Sprite selected;
    // private SkadedyrView skadedyrView;

    public CatMenu() {
        // this.skadedyrView = skadedyrView;
        menuRect.x = 832;
        menuRect.y = 0;
        menuRect.width = SkadedyrView.screenRect.x - 800;
        menuRect.height = SkadedyrView.screenRect.y;
        this.availableCatsList = new ArrayList<>();

    }
    public void init(){
        availableCatsList.add(new Sprite(new Texture(Gdx.files.internal("cat.png"))));
        availableCatsList.add(new Sprite(new Texture(Gdx.files.internal("angryCat.png"))));
        availableCatsList.add(new Sprite(new Texture(Gdx.files.internal("freezeCat.png"))));
        availableCatsList.add(new Sprite(new Texture(Gdx.files.internal("hagleKatt.png"))));
        selected = availableCatsList.get(0);
    }

    public Rectangle getMenuRectangle() {
        return menuRect;
    }

    public void draw(SpriteBatch batch) {
        int i = 0;
        for (Sprite sprite : availableCatsList) {
            batch.draw(sprite, getX() + MARGIN, MARGIN + i * CATDIM, CATDIM, CATDIM);
            i++;
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(getX(), getY(), getW(), getH());
        shapeRenderer.circle(selected.getX(), selected.getY(), CATDIM);
    }
    

    public Sprite getSprite(int index) {
        return availableCatsList.get(index);
    }

    public void selector(Vector2 pos){
        for (Sprite sprite : availableCatsList) {
            if (sprite.getBoundingRectangle().contains(pos))
                selected = sprite;
        }
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
