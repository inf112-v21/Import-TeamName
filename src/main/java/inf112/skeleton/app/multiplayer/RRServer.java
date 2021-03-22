package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.multiplayer.NetworkPackets.Entry;
import inf112.skeleton.app.game.MainGame;

import java.io.IOException;

public class RRServer {

    //Server Object
    Server server;
    MainGame game;



    public RRServer() throws IOException {
        Log.set(Log.LEVEL_DEBUG);  //set to Log.LEVEL_DEBUG if needed
        //board = new BoardState/Board/GameMap(this);

        server = new Server() {
            protected Connection newConnection() {  //Storing by connection state
                return new RRConnection();
            }
        };

        //Bind to port
        server.bind(NetworkPackets.tcpPort, NetworkPackets.udpPort);

        server.start();

        //Registering of packet class is being done all at once at NetworkPackets.java
        NetworkPackets.register(server);
        game = new MainGame();



        server.addListener(new Listener() {
            public void received(Connection c, Object packet) {
                RRConnection connection = (RRConnection) c; //every connection is of RRConnection, so this should be fine.

                if (packet instanceof Entry) {
                    Entry type = ((Entry) packet); //casting to access the packet

                    //from here on, im just trying to catch invalid names
                    if (connection.name != null) return;

                    String named = type.name; //since Entry might get other values in the future.
                    if (named == null) return;

                    named = named.trim();
                    if (named.length() == 0) return;

                    connection.name = named; //should be a valid name by this point.



                }

            }

                public void disconnected (Connection c) {
                    RRConnection connection = (RRConnection) c;
                    //do something here, to be implemented, maybe an if connection.name != null or something, do {message}
                    System.out.println("A client disconnected."); //testing
                }
        });
    }


    static class RRConnection extends Connection {
        public String name;
    }

    public void ceaseServer() {
        server.close();
        server.stop();
    }


}
