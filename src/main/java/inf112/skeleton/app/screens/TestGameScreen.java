package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.multiplayer.RRClient;
import inf112.skeleton.app.multiplayer.RRServer;
import inf112.skeleton.app.screens.GameScreen;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.io.IOException;

import static com.badlogic.gdx.Gdx.gl;

public class TestGameScreen implements Screen {
    private boolean hosting;
    private String ip;
    private RRServer server;
    private RRClient client;
    private String name;

    RoboRally game;


    public TestGameScreen (RoboRally game, boolean hosting, String ip, String name) {
        this.game = game;
        this.hosting = hosting;
        this.name = name;
        if(!ip.isEmpty()) {
            this.ip = ip;
        } else {
            this.ip = "localhost";
        }
    }

    public void show() {
        client = new RRClient(name);

        if(hosting) {
            Log.info("starting server");
            try {
                server = new RRServer();
                client.connect("localhost");
            } catch (IOException e) {
                e.printStackTrace();  //no idea what this does, haven't read the documentation on IOException
                Log.info("Unable to start server.");
                Gdx.app.exit();
            }
        } else {
            client.connect(ip);
        }
    }

    public void render(float delta) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //render the map
        game.render();


    }

    public void hide() {
        client.ceaseClient();
        if (server != null) {
            server.ceaseServer();
        }
    }

    public void resume() {
    }

    public void resize(int width, int height){
    }

    public void pause () {
    }

    public void dispose () {
    }
}
