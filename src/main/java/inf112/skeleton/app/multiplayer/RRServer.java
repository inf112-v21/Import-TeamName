package inf112.skeleton.app.multiplayer;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.multiplayer.NetworkPackets.Entry;

import java.io.IOException;

public class RRServer {

    // Server Object
    Server server;
    MainGame mainGame;
    private TiledMap map;


    public RRServer(MainGame mainGame) throws IOException {
        this.mainGame = mainGame;
        // Log.set(Log.LEVEL_DEBUG);

        this.server = new Server() {
            protected Connection newConnection() {  // Storing by connection state
                return new RRConnection();
            }
        };

        // Bind to port
        this.server.bind(NetworkPackets.tcpPort, NetworkPackets.udpPort);

        this.server.start();
        this.map = new TmxMapLoader().load("Maps/Chess.tmx");
        mainGame.setup(this.map);

        // Registering of packet class is being done all at once at NetworkPackets.java
        NetworkPackets.register(this.server);

        this.server.addListener(new Listener() {
            public void received(Connection c, Object packet) {
                RRConnection connection = (RRConnection) c; // Every connection is of RRConnection, so this should be fine.

                if (packet instanceof Entry) {
                    Entry type = ((Entry) packet); // Casting to access the packet

                    // From here on, im just trying to catch invalid names
                    if (connection.name != null) return;

                    String named = type.name; // Since Entry might get other values in the future.
                    if (named == null) return;

                    named = named.trim();
                    if (named.length() == 0) return;

                    connection.name = named; // Should be a valid name by this point.

                    NetworkPackets.NewPlayer message = new NetworkPackets.NewPlayer(connection.name, connection.getID(), true);
                    server.sendToAllTCP(message);

                    for (Connection connections: server.getConnections()) {
                        RRConnection rrConnection = (RRConnection) connections; // Casting just in case it uses default connection instead

                        // This here, had problems with adding players, at times it overlapped players at same spot
                        // This "if" checks if it's yourself who is logging in. thus stopping ^ probably...
                        if (rrConnection.getID() != connection.getID() && rrConnection.name != null) {
                            NetworkPackets.NewPlayer message2 = new NetworkPackets.NewPlayer(rrConnection.name, rrConnection.getID(), true);
                            connection.sendTCP(message2);
                        }
                    } return;
                } else if (packet instanceof NetworkPackets.MovedRobot) {
                    NetworkPackets.MovedRobot type = (NetworkPackets.MovedRobot) packet;
                    type.playerID = connection.getID();
                    server.sendToAllExceptUDP(connection.getID(), (type));
/**
                    for (Connection connections: server.getConnections()) {
                        RRConnection rrConnection = (RRConnection) connections;
                        if (rrConnection.getID() != connection.getID() && rrConnection.name != null) {
                            server.sendToAllExceptUDP(connection.getID(),(type));
                            //server.sendToAllUDP(type);
                        }
                    }
 */
               } // If a packet of unknown class gets sent from client somehow.
                else if (!(packet instanceof FrameworkMessage.Ping) && !(packet instanceof FrameworkMessage.KeepAlive)) {
                    Log.info("An unknown packet was sent from Client.");
                }

            }

                public void disconnected (Connection c) {
                    RRConnection connection = (RRConnection) c;
                    if (connection.name != null) {
                        System.out.println(connection.name + " has disconnected."); // Testing
                        NetworkPackets.NewPlayer leaveMessage = new NetworkPackets.NewPlayer(connection.name, connection.getID(), false);
                        server.sendToAllTCP(leaveMessage);
                    }
                }
        });
    }


    static class RRConnection extends Connection {
        public String name;
    }

    public void ceaseServer() {
        this.server.close();
        this.server.stop();
    }

    public TiledMap getMap() {
        return this.map;
    }
}
