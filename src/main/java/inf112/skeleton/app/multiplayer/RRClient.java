package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.kryo.*;


import inf112.skeleton.app.multiplayer.NetworkPackets.Entry;

import java.io.IOException;
import java.util.logging.Handler;

public class RRClient {

    private String name;
    private Client client;
    private int id;



    public RRClient (String name) {
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

    protected void connectionHandler (Connection c) {
        id = c.getID();
        c.getRemoteAddressTCP().toString();
        Entry assignName = new Entry(name);
        client.sendTCP(assignName);
        client.updateReturnTripTime();
    }

    protected void disconnectHandler (Connection c) {
        //add some method which clears disconnected player/depends on game rules. Probably has to be done in some other class.
    }

    public void packetHandler (int playerId, Object packet) {
        if (packet instanceof NetworkPackets.NewPlayer) {
            NetworkPackets.NewPlayer type = (NetworkPackets.NewPlayer) packet; //casting to access the packet
            System.out.println(type.name + "has joined");
        }

    }

    public void ceaseClient() {
        client.stop();
        client.close();
    }

    public void ping() {
        if (client.isConnected()) { this.client.updateReturnTripTime(); }
    }




}
