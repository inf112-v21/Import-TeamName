package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.multiplayer.NetworkPackets.Entry;

import java.io.IOException;

public class RRServer {

    //Server Object
    Server server;



    public RRServer() throws IOException {
        Log.set(Log.LEVEL_INFO);

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

                    NetworkPackets.NewPlayer message = new NetworkPackets.NewPlayer(connection.name, connection.getID());
                    server.sendToAllExceptTCP(connection.getID(), message); //messaging everyone except new person, that new person joined.



                }

            }

                public void disconnected (Connection c) {
                    RRConnection connection = (RRConnection) c;
                    if (connection != null) {
                        System.out.println(connection.name + " has disconnected."); //testing
                    }
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
