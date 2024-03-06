package inf112.skeleton.app.model.catmenu;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.model.entities.AngryCat;
import inf112.skeleton.app.model.entities.BasicCat;
import inf112.skeleton.app.view.SkadedyrView;

public class CatMenu {

    private HashMap<Texture, Rectangle> availableCatsMap;
    private Set<Texture> availableCatsList;
    private Rectangle menuRect = new Rectangle();
    // private SkadedyrView skadedyrView;

    public CatMenu(SkadedyrView skadedyrView) {
        // this.skadedyrView = skadedyrView;
        menuRect.x = 832;
        menuRect.y = 0;
        menuRect.width = skadedyrView.getScreenWidth() - 800;
        menuRect.height = skadedyrView.getScreenHeight();

        this.availableCatsMap = new HashMap<Texture, Rectangle>();
        availableCatsMap.put(BasicCat.texture, new Rectangle(0, 0, 0, 0));
        availableCatsMap.put(AngryCat.texture, new Rectangle(0, 0, 0, 0));
        availableCatsList = availableCatsMap.keySet();

    }

    public Rectangle getMenuRectangle() {
        return menuRect;
    }

    public Rectangle getCatsMap(Texture tex) {
        return availableCatsMap.get(tex);
    }

    public Set<Texture> getTextures(){
        return availableCatsList;
    }
    public float getX() {
        return menuRect.x;
    }

    public float getY() {
        return menuRect.y;
    }

    public float getH() {
        return menuRect.height;
    }

    public float getW() {
        return menuRect.width;
    }

}
