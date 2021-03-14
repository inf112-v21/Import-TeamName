package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

public class RRServer {

    //Server Object
    Server server;

    //private BoardState/Board/GameMap board;


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

            //fill here, what server should do stuff.

        });


        server.start();

        System.out.println("Server is up and running"); //yet another test/confirmation
    }

    //is run when a packet is received.
    public void received(Connection c, Object packet) { //should shove this into listener, but no idea what to place in listener atm.
        RRConnection connection = (RRConnection) c; //every connection is of RRConnection, so this should be fine.
    }
    public void disconnected (Connection c) {
        RRConnection connection = (RRConnection) c;
        //do something here, to be implemented, maybe an if connection.name != null or something, do {message}
        System.out.println("A client disconnected."); //testing
    }

    static class RRConnection extends Connection { //could also use a static hashmap to be honest.
        public String name;
    }


}
