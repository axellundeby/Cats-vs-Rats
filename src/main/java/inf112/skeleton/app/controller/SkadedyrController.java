package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.Cat;

public class SkadedyrController {
    private final SkadedyrModel model;
    boolean speedUp = false;
    private Task currentClockTickTask = null;

    public SkadedyrController(SkadedyrModel model) {
        this.model = model;
    }

    /**
     * Starts a timer that triggers a clock tick in the game model at regular
     * intervals.
     * The interval is determined by the speed of the game model.
     * If a timer is already running, it is cancelled before the new timer is
     * started.
     */
    public void startTimer() {
        float delay = 0;
        if (currentClockTickTask != null) {
            currentClockTickTask.cancel();
        }
        currentClockTickTask = clockTick();
        Timer.schedule(currentClockTickTask, delay, model.getSpeed());
    }

      public void handleUserInput() {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        Vector2 mouse = new Vector2(mouseX, 842 - mouseY);
        if (Gdx.input.isTouched() && mouseY > 100 && mouseY < 650) {
            newCat(mouseX, 842 - mouseY);
            model.getCatMenu().deselect();
        }
        if (Gdx.input.isTouched()) {
            model.getCatMenu().selector(mouse);
            selectCat(mouse);
        }
    }

    private void newCat(float mouseX, float mouseY) {
        Cat cat = model.getCatMenu().getSelectedCat();
        if (cat != null) {  
            int cost = cat.getCost();
            if (model.getMoney() >= cost) {
                cat.setPos(mouseX, mouseY);  
                model.addCat(cat);
                model.setMoney(model.getMoney() - cost);
            }
        } else {
            model.pressedUppgradeButton();
        }
    }

    private void selectCat(Vector2 mouse) {
        if (mouse.y < 200) return;
        for (Cat cat : model.getCats()) {
            if (cat.getSelectionCircle().contains(mouse)) {
                model.setSelectedCat(cat);
                return;
            }
        }
        model.setSelectedCat(null);
    }

    private Task clockTick() {
        return new Task() {
            @Override
            public void run() {
                startTimer();
                handleUserInput();
                if (model.isPaused()) {
                    return; 
                }
                model.clockTick();
            }
        };
    }
}