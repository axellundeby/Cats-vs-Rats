package inf112.skeleton.app.model;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.app.model.Coordinate;
import inf112.skeleton.app.view.SkadedyrView;

import com.badlogic.gdx.graphics.Texture;

public class SkadedyrModel {
    private float dx = 1, dy = 1;

    private ArrayList<Cat> cats;
    private ArrayList<Rat> aliveRats;

    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.aliveRats = new ArrayList<>();
    }

    // Når katte- og rotteobjekter konstrueres, legges de inn i disse listene
    public void addCat(Cat cat){
        cats.add(cat);
    }
    public void addRat(Rat rat){
        aliveRats.add(rat);
    }

    public ArrayList<Cat> getCats(){
        return cats;
    }
    public ArrayList<Rat> getRats(){
        return aliveRats;
    }


    public void uselessfunction(Rectangle spriteRect, Rectangle screenRect) { // for å beholde koden

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        // Move the alligator a bit. You normally shouldn't mix rendering with logic in
        // this way. (Also, movement should probably be based on *time*, not on how
        // often we update the graphics!)
        Rectangle.tmp.set(spriteRect);
        Rectangle.tmp.x += dx;
        Rectangle.tmp2.set(spriteRect);
        Rectangle.tmp2.y += dy;
        if (screenRect.contains(Rectangle.tmp))
            spriteRect.x += dx;
        else
            dx = -dx;
        if (screenRect.contains(Rectangle.tmp2))
            spriteRect.y += dy;
        else
            dy = -dy;

        // Don't handle input this way – use event handlers!
        if (Gdx.input.justTouched()) { // check for mouse click

            // bellSound.play();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) { // check for key press
            Gdx.app.exit();
        }

    }


}
