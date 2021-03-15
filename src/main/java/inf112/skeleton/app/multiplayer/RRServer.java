package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

public class RRServer {

    //Server Object
    Server server;



    public RRServer() throws IOException {
        Log.set(Log.LEVEL_INFO);  //set to Log.LEVEL_DEBUG if needed
        //board = new BoardState/Board/GameMap(this);

        //Registering of packet class is being done all at once at NetworkPackets.java
        NetworkPackets.register(server);

        server = new Server() {
            protected Connection newConnection() {  //Storing by connection state
                return new RRConnection();
            }
        };



        //Bind to port
        server.bind(NetworkPackets.tcpPort, NetworkPackets.udpPort);

        server.addListener(new Listener() {
            public void received(Connection c, Object packet) {
                RRConnection connection = (RRConnection) c; //every connection is of RRConnection, so this should be fine.

                //fill in with if packet instanceof object/packet
                //if (packet instanceof )
            }


        });


        server.start();

        System.out.println("Server is up and running"); //yet another test/confirmation
    }

    public void disconnected (Connection c) {
        RRConnection connection = (RRConnection) c;
        //do something here, to be implemented, maybe an if connection.name != null or something, do {message}
        System.out.println("A client disconnected."); //testing
    }

    static class RRConnection extends Connection {
        public String name;
    }

    public void ceaseServer() {
        server.close();
        server.stop();
    }


}
