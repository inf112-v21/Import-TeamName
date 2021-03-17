package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.logging.Handler;

public class RRClient {

    private String name;
    private Client client;
    private int id;



    public RRClient (String name) throws Exception {
        this.name = name;

        client = new Client();
        client.start();

        NetworkPackets.register(client);

        client.addListener(new Listener() {
            public void connected(Connection c) {
                connectionHandler(c);
            }
            public void received(Connection c, Object packet) {
                packetHandler(c.getID(), packet);
            }
            public void disconnected(Connection c) {
                disconnectHandler(c);
            }
        });
    }


    public void connect(String ip) {
        try {
            client.connect(5000, ip, NetworkPackets.tcpPort, NetworkPackets.udpPort);
        } catch (IOException e) {
            Log.info("Failed to connect to " + ip);
        }
    }

    public void localConnect() {
        connect("localhost");
    }

    protected void connectionHandler (Connection c) {
        id = c.getID();
        c.getRemoteAddressTCP();
    }

    protected void disconnectHandler (Connection c) {
        //add some method which clears disconnected player/depends on game rules. Probably has to be done in some other class.
    }

    public void packetHandler (int playerId, Object packet) {
        //fill in with a bunch of if/else depending on instanceof object/packet

    }

    public void ceaseClient() {
        client.stop();
        client.close();
    }

    public void ping() {
        if (client.isConnected()) { this.client.updateReturnTripTime(); }
    }




}