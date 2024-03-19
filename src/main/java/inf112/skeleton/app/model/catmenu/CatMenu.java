package inf112.skeleton.app.model.catmenu;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.view.SkadedyrView;

public class CatMenu {

    private HashMap<Texture, Rectangle> availableCatsMap;
    private ArrayList<Cat> availableCatsList;
    private Rectangle menuRect = new Rectangle();
    // private SkadedyrView skadedyrView;

    public CatMenu(SkadedyrModel model) {
        // this.skadedyrView = skadedyrView;
        menuRect.x = 832;
        menuRect.y = 0;
        menuRect.width = SkadedyrView.screenRect.x - 800;
        menuRect.height = SkadedyrView.screenRect.y;
        availableCatsList = model.getCats();

    }

    public Rectangle getMenuRectangle() {
        return menuRect;
    }

    public void draw(SpriteBatch batch) {
        int i = 0;
        for (Cat sprite : availableCatsList) {
            batch.draw(sprite.getSprite(), getX() + 10 + i * 30, 10 + i * 30, 200, 200);
            i++;
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(getX(), getY(), getW(), getH());
        shapeRenderer.end();
    }

    public Rectangle getCatsMap(Texture tex) {
        return availableCatsMap.get(tex);
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
