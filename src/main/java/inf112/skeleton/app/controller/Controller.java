package inf112.skeleton.app.controller;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.SkadedyrView;

public class Controller  {

    private final SkadedyrModel model;
    private final SkadedyrView view;

    public Controller(SkadedyrModel model, SkadedyrView view){
        this.model = model;
        this.view = view;
    }

    public void create() {
        // Schedule a task to be executed every n milliseconds
        float delay = 0; // delay before the task is first executed
        float intervalSeconds = 1; // interval in seconds between executions of the task

        Timer.schedule(clockTick(), delay, intervalSeconds);
    }

    private Task clockTick() {
        // Return a new Task with the overridden run method
        return new Task() {
            @Override
            public void run() {
                // This code will be executed every n seconds
                System.out.println("Tick!");
            }
        };
    }
    
}
