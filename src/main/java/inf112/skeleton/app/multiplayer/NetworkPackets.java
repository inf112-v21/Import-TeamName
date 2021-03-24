package inf112.skeleton.app.multiplayer;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Generics;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.FrameworkMessage;

public class NetworkPackets {

    static public final int tcpPort = 8080;
    static public final int udpPort = 8080;


    static public void register (EndPoint endPoint) { //EndPoint is either server og client
       Kryo obj = endPoint.getKryo(); //instead of writing the latter on every object to be registered.

      //  obj.register(Vector2.class); //follow this format of "obj.register(xxx.class)", where xxx is class.
        obj.register(FrameworkMessage.Ping.class);
        obj.register(Entry.class);
        obj.register(NewPlayer.class);
    }

    static public class Entry {  //still testing, might need more here
        public String name;

        public Entry() {}
        public Entry(String name) {
            this.name = name;
        }
    }

    static public class NewPlayer {
        public String name;
        public int playerId;

        public NewPlayer() {}
        public NewPlayer (String name, int playerId) {
            this.name = name;
            this.playerId = playerId;

        }
    }

    //implement the classes for use in RRClient/RRServer here

}
