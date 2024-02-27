package inf112.skeleton.app.main;

import com.badlogic.gdx.ApplicationListener;


import inf112.skeleton.app.controller.Controller;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.SkadedyrView;

public class SkadedyrGame implements ApplicationListener{

    private final SkadedyrModel model;
    private final SkadedyrView view;
    private final Controller controller;

    public SkadedyrGame(){
        this.model  = new SkadedyrModel();
        this.view = new SkadedyrView(model);
        this.controller = new Controller(model, view);
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
        view.draw();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void dispose() {
        view.dispose();
    }
}
