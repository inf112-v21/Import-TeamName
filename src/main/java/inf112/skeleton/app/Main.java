package inf112.skeleton.app;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.lwjgl.system.CallbackI;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("ROBORALLY");
        cfg.setWindowedMode(1000,1000);

        //HelloWorld helloWorld = new HelloWorld();
        TestGame game = new TestGame();
        new Lwjgl3Application(game, cfg);

    }
}