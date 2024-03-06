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
    private float spawnTimer = 0;
    private int ratSpawnDelay = 5;
    private boolean gameRunning = true;
    private Rat rat;

    public SkadedyrController(SkadedyrModel model, SkadedyrView view){
        this.model = model;
        this.view = view;
    }

    public void startTimer() {
        // Schedule a task to be executed every n milliseconds
        float delay = 0; // delay before the task is first executed
        float intervalSeconds = (float) 0.01; // interval in seconds between executions of the task

        Timer.schedule(clockTick(), delay, intervalSeconds);
    }

    private Task clockTick() {
        // Return a new Task with the overridden run method
        return new Task() {
            @Override
            public void run() {
                spawnTimer += 0.05;
                // This code will be executed every n seconds
                int mouseX = Gdx.input.getX();
                int mouseY = Gdx.input.getY();
                model.moveRats();
                model.attackRat();
                model.attackRatsForEachCat();
                //kanskje en sleep
                
                if(spawnTimer > ratSpawnDelay && model.getRatsSpawned() < model.getRatLimitPerLevel()){ 
                    model.spawnRats();
                    spawnTimer = 0;
                }

                //aner ikke om dette fungerer, men rotter skal være fryst i 5 sekunder
                // if(rat.isFrozen()){
                //     rat.freezeTimer += 0.05;
                //     if(rat.freezeTimer > 5){
                //         rat.isFrozen = false;
                //         rat.freezeTimer = 0;
                //     }
                // }

                if (Gdx.input.isTouched()) { // check for mouse click
                    model.newCat(mouseX, 842-mouseY);
                    //legg til sleep på drept animasjon og freeze
                }
                
                for (Rat rat : model.getRats()) {
                    rat.addTime();
                }

            }
        };
    }
    
}
