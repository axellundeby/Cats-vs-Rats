package inf112.skeleton.app.model;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.model.Coordinate;
import inf112.skeleton.app.view.View;

import com.badlogic.gdx.graphics.Texture;

public class Model implements ApplicationListener {
    private View view;
    private float dx = 1, dy = 1;

    public Model() {
        this.view = new View();
    }

    @Override
    public void create() {
        // Called at startup
        view.create();

    }

    @Override
    public void dispose() {
        // Called at shutdown
        view.dispose();

    }

    @Override
    public void render() {
        // Called when the application should draw a new frame (many times per second).
        view.draw();

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

    @Override
    public void resize(int width, int height) {
        // Called whenever the window is resized (including with its original site at
        // startup)
        view.resize(width, height);

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
