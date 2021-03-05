package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.map.Tiles;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Import team name: Robo Rally");
        cfg.setWindowedMode(1000,1000);
        RoboRally roboRally = new RoboRally();

        // Turn debug mode on or off
        // roboRally.debugModeOn();
        new Lwjgl3Application(roboRally, cfg);
    }
}