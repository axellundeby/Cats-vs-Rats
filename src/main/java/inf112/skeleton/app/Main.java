package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import inf112.skeleton.app.model.Cat;
import inf112.skeleton.app.view.View;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Skadedyrkontrollørerne");
        cfg.setWindowedMode(480, 320);


        new Lwjgl3Application(new View(), cfg);

    }
}