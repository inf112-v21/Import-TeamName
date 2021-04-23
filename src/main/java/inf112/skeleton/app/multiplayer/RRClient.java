package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.multiplayer.NetworkPackets.Entry;

import java.io.IOException;

public class RRClient {

    private String name;
    private Client client;
    MainGame mainGame;
    private int id;



    public RRClient (String name, MainGame mainGame) {
        this.name = name;
        this.mainGame = mainGame;

        this.client = new Client();
        this.client.start();

        // Registered by kryo at networkpackets, both for server and client register the same packets there.
        NetworkPackets.register(this.client);

        this.client.addListener(new Listener() {
            public void connected(Connection c) {
                connectionHandler(c);
            }
            public void received(Connection c, Object packet) {
                packetHandler(c.getID(), packet);
            }
            public void disconnected(Connection c) {
            }
        });
    }


    public void connect(String ip) {
        try {
            this.client.connect(5000, ip, NetworkPackets.tcpPort, NetworkPackets.udpPort);
        } catch (IOException e) {
            Log.info("Failed to connect to " + ip);
        }
    }


    protected void connectionHandler (Connection c) {
        this.id = c.getID();
        c.getRemoteAddressTCP().toString(); // Get IP address
        Entry assignName = new Entry(this.name);
        this.client.sendTCP(assignName);
        this.client.updateReturnTripTime();
    }

    // What to do with packet of type "x"
    public void packetHandler (int playerId, Object packet) {
        if (packet instanceof NetworkPackets.NewPlayer) {
            NetworkPackets.NewPlayer type = (NetworkPackets.NewPlayer) packet; // Casting to access the packet
            if (type.joining) {
                System.out.println(type.name + " has joined.");
                this.mainGame.multiplayerAddPlayer(type.playerID);
            } else {
                System.out.println(type.name + " has left.");
                this.mainGame.removePlayer(type.playerID);
            }
        } else if (packet instanceof NetworkPackets.MovedRobot) {
            NetworkPackets.MovedRobot type = (NetworkPackets.MovedRobot) packet;
            this.mainGame.cheatPosition(type);
        }
    }

    // Send UDP packet
    public void sendPacketUDP(Object packet) {
        if (this.client.isConnected()) {
            this.client.sendUDP(packet);
        }
    }

    // Shutting off the client
    public void ceaseClient() {
        this.client.stop();
        this.client.close();
    }

}
