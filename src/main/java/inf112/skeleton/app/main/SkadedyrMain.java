package inf112.skeleton.app.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class SkadedyrMain {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Skadedyrkontrollørerne");
        cfg.setWindowedMode(1200, 842);
        cfg.setResizable(false);


        new Lwjgl3Application(new SkadedyrGame(), cfg);

    }
}