package inf112.skeleton.app.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import inf112.skeleton.app.controller.SkadedyrController;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.LibGDXResourceFactory;
import inf112.skeleton.app.view.SkadedyrView;
import inf112.skeleton.app.view.TimeSource;

public class SkadedyrGame implements ApplicationListener{

    private final SkadedyrModel model;
    private final SkadedyrView view;
    private final SkadedyrController controller;
    private TimeSource timeSource;

    public SkadedyrGame(){
        GameResourceFactory factory = new LibGDXResourceFactory();
        TimeSource timeSource = new TimeSource() {

            @Override
            public float getDeltaTime() {
                return Gdx.graphics.getDeltaTime();
            }
            
        };
        this.model  = new SkadedyrModel(factory, timeSource);
        this.controller = new SkadedyrController(model);
        this.view = new SkadedyrView(model, factory);
       

    }

    @Override
    public void create() {
        controller.startTimer();
        //timeSource.getDeltaTime();
        view.create();
        model.initCatMenu();
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
        model.setPause();
    }

    @Override
    public void resume() {
        model.setPause();

    }

    @Override
    public void dispose() {
        view.dispose();
    }
}
