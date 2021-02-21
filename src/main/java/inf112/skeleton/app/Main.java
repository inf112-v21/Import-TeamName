package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Import team name: Robo Rally");
        cfg.setWindowedMode(1000,1000);

        RoboRally roboRally = new RoboRally();
        GameWithScreens gameWithScreens = new GameWithScreens();
        new Lwjgl3Application(gameWithScreens, cfg);

    }
}