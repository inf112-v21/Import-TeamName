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

        client = new Client();
        client.start();

        //registered by kryo at networkpackets, both for server and client register the same packets there.
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

    //what to do with packet of type "x"
    public void packetHandler (int playerId, Object packet) {
        if (packet instanceof NetworkPackets.NewPlayer) {
            NetworkPackets.NewPlayer type = (NetworkPackets.NewPlayer) packet; //casting to access the packet
            if (type.joining = !false) {
                System.out.println(type.name + "has joined"); //to console atm
                mainGame.multiplayerAddPlayer(type.playerID);
            } else {
                System.out.println(type.name + "has leefffttt");
                mainGame.removePlayer(type.playerID);
            }
        } else if (packet instanceof NetworkPackets.MovedRobot) {
            NetworkPackets.MovedRobot type = (NetworkPackets.MovedRobot) packet;
            mainGame.cheatPosition(type);
            System.out.println("A robot moved!");
        }
    }

    //send UDP packet
    public void sendPacketUDP(Object packet) {
        if (client.isConnected()) {
            client.sendUDP(packet);
        }
    }

    //send TCP packet
    public void sendPacketTCP(Object packet) {
        if (client.isConnected()) {
            client.sendTCP(packet);
        }
    }

    //shutting off the client
    public void ceaseClient() {
        client.stop();
        client.close();
    }

    public void ping() {
        if (client.isConnected()) { this.client.updateReturnTripTime(); }
    }




}
