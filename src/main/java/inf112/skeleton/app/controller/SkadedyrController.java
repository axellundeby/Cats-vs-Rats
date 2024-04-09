package inf112.skeleton.app.controller;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.app.model.SkadedyrModel;

public class SkadedyrController {
    private final SkadedyrModel model;
    boolean speedUp = false;
    private Task currentClockTickTask = null;
    public SkadedyrController(SkadedyrModel model) {
        this.model = model;
    }

    public void startTimer() {
        float delay = 0;
        if (currentClockTickTask != null) {
            currentClockTickTask.cancel();
        }
        currentClockTickTask = clockTick();
        Timer.schedule(currentClockTickTask, delay, model.getSpeed());
    }

    private Task clockTick() {
        return new Task() {
            @Override
            public void run() {
                startTimer();
                if (model.isPaused()) {
                    return; // Skip game logic if paused
                }
                model.clockTick();
            }
        };
    }
}