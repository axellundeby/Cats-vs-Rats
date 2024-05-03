package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.app.model.ISkadedyrModel;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.cat.ICat;
import inf112.skeleton.app.model.entities.cat.ShotgunCat;
import inf112.skeleton.app.view.GameResourceFactory;

public class SkadedyrController {
    private final ISkadedyrModel model;
    private Task currentClockTickTask = null;
    private final GameResourceFactory resourceFactory;

    public SkadedyrController(ISkadedyrModel model, GameResourceFactory resourceFactory) {
        this.model = model;
        this.resourceFactory = resourceFactory;
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

    private void handleUserInput() {
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
        ICat selectedTemplate = model.getCatMenu().getSelectedCat();
        if (selectedTemplate == null) {
            model.pressedUppgradeButton();
            return; 
        }
        ICat newCat = null;
        if (selectedTemplate instanceof BasicCat) {
            newCat = new BasicCat(resourceFactory);
        } else if (selectedTemplate instanceof ShotgunCat) {
            newCat = new ShotgunCat(resourceFactory);
        } else if (selectedTemplate instanceof FreezeCat) {
            newCat = new FreezeCat(resourceFactory);
        }
    
        if (newCat != null) {
            int cost = newCat.getCost();
            if (model.getMoney() >= cost) {
                newCat.setPos(mouseX, mouseY);
                model.addCat(newCat);
                model.setMoney(model.getMoney() - cost);
            } else {
                model.setErrorText();
            }
        } 
    }
    
    private void selectCat(Vector2 mouse) {
        if (mouse.y < 200) return;
        for (ICat cat : model.getCats()) {
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