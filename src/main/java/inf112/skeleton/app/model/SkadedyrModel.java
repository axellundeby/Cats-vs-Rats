package inf112.skeleton.app.model;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.model.entities.BasicCat;
import inf112.skeleton.app.model.entities.BasicRat;
import inf112.skeleton.app.model.entities.Cat;
import inf112.skeleton.app.model.entities.Rat;

public class SkadedyrModel implements ISkadedyrModel {
    private float dx = 1, dy = 1;

    private ArrayList<Cat> cats;
    private ArrayList<Rat> aliveRats;

    private Rat testRat;

    public SkadedyrModel() {
        this.cats = new ArrayList<>();
        this.aliveRats = new ArrayList<>();
        
    }

    @Override
    public void addCat(Cat cat) {
        cats.add(cat);
    }

    @Override
    public void addRat(Rat rat) {
        aliveRats.add(rat);
    }

    @Override
    public ArrayList<Cat> getCats() {
        return cats;
    }

    @Override
    public ArrayList<Rat> getRats() {
        return aliveRats;
    }

    @Override
    public void moveRats() {
        for (Rat rat : aliveRats) {
            rat.move(dx);
        }
    }

    public void uselessfunction(Rectangle spriteRect, Rectangle screenRect) { // for å beholde koden

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

    public void update() {
        this.testRat = new BasicRat();
        addRat(testRat);
    }

    public void mousePos(int mouseX, int mouseY) {
    }

    public void newCat(int mouseX, int mouseY) {
        Cat catTest = new BasicCat();
        catTest.setPos(mouseX, mouseY);
        addCat(catTest);
        System.out.println(mouseX + ", " + mouseY);
    }

}
