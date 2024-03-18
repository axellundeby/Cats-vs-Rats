package inf112.skeleton.app.main;

import com.badlogic.gdx.ApplicationListener;


import inf112.skeleton.app.controller.SkadedyrController;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.SkadedyrView;

public class SkadedyrGame implements ApplicationListener{

    private final SkadedyrModel model;
    private final SkadedyrView view;
    private final SkadedyrController controller;

    public SkadedyrGame(){
        this.model  = new SkadedyrModel();
        this.view = new SkadedyrView(model);
        this.controller = new SkadedyrController(model);

    }

    @Override
    public void create() {
        controller.startTimer();
        view.create();
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }

    @Override
    public void render() {
        view.render();
       
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        view.dispose();
    }
}
