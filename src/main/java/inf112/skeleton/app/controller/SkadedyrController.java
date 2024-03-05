package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.Rat;
import inf112.skeleton.app.view.SkadedyrView;

public class SkadedyrController  {

    private final SkadedyrModel model;
    private final SkadedyrView view;

    public SkadedyrController(SkadedyrModel model, SkadedyrView view){
        this.model = model;
        this.view = view;
    }

    public void startTimer() {
        // Schedule a task to be executed every n milliseconds
        float delay = 0; // delay before the task is first executed
        float intervalSeconds = (float) 0.05; // interval in seconds between executions of the task

        Timer.schedule(clockTick(), delay, intervalSeconds);
    }

    private Task clockTick() {
        // Return a new Task with the overridden run method
        return new Task() {
            @Override
            public void run() {
                // This code will be executed every n seconds
                int mouseX = Gdx.input.getX();
                int mouseY = Gdx.input.getY();
                // model.mousePos();
                model.moveRats();

                if (Gdx.input.isKeyPressed(Input.Keys.U)){
                    System.out.println("Key 'U' pressed");
                    model.update();
                }
                
                if (Gdx.input.isTouched()) { // check for mouse click
                    model.newCat(mouseX, 842-mouseY);

                }
                
                for (Rat rat : model.getRats()) {
                    rat.addTime();
                }

            }
        };
    }
    
}
