package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import inf112.skeleton.app.controller.Controller;
import inf112.skeleton.app.model.Model;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Skadedyrkontrollørerne");
        cfg.setWindowedMode(1200, 842);


        new Lwjgl3Application(new Model(), cfg);

    }
}