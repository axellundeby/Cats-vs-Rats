package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.app.main.SkadedyrMain;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.view.SkadedyrView;

public class SkadedyrController {

    private final SkadedyrModel model;
    private final SkadedyrView view;
    private boolean pausedGame = true;
    private boolean keyPHandled = false;
    private boolean keySHandled = false;
    private boolean keyRHandled = false;
    boolean speedUp = false;
    private float intervalSeconds = (float) 0.05; // interval in seconds between executions of the task
    private Task currentClockTickTask = null;
    private boolean gameRunning = true;

    public SkadedyrController(SkadedyrModel model, SkadedyrView view) {
        this.model = model;
        this.view = view;
    }

    public void startTimer() {
        // Schedule a task to be executed every n milliseconds
        float delay = 0; // delay before the task is first executed

        if (currentClockTickTask != null) {
            currentClockTickTask.cancel();
        }

        currentClockTickTask = clockTick();
        Timer.schedule(currentClockTickTask, delay, intervalSeconds);
        // Timer.schedule(clockTick(), delay, intervalSeconds);
    }

    private Task clockTick() {
        // Return a new Task with the overridden run method
        return new Task() {
            @Override
            public void run() {

                // Check if 'P' is pressed and keyPHandled is false
                if (Gdx.input.isKeyPressed(Input.Keys.P) && !keyPHandled) {
                    pausedGame = !pausedGame;
                    keyPHandled = true; // Prevent further toggling until key is released

                }
                if (!Gdx.input.isKeyPressed(Input.Keys.P)) {
                    keyPHandled = false; // Allow toggling again once the key is released
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S) && !keySHandled && !pausedGame) {
                    updateClockTick();
                    startTimer();
                    keySHandled = true;
                    System.out.println(keySHandled);
                }
                if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
                    keySHandled = false; // Allow toggling again once the key is released
                }
                if ((Gdx.input.isKeyPressed(Input.Keys.R))) { // Press r to restart
                    SkadedyrMain.main(null);
                }

                if (pausedGame) {
                    return; // Skip game logic if paused
                }

                model.clockTick();

            }
        };
    }

    public void updateClockTick() {
        System.out.println(intervalSeconds);
        if (intervalSeconds == (float) 0.05) {
            intervalSeconds = (float) 0.0025;

        } else {
            intervalSeconds = (float) 0.05;
        }
        System.out.println(intervalSeconds);
    }

}
