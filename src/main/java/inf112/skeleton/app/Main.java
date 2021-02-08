package inf112.skeleton.app;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.lwjgl.system.CallbackI;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Test-prosjekt");
        cfg.setWindowedMode(2560, 1440);

        HelloWorld helloWorld = new HelloWorld();
        new Lwjgl3Application(helloWorld, cfg);

    }
}