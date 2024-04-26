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

    private Task clockTick() {
        return new Task() {
            @Override
            public void run() {
                startTimer();
                if (model.isPaused()) {
                    model.handleUserInput();
                    return; 
                }
                model.clockTick();
            }
        };
    }
}