package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.FrameworkMessage;

public class NetworkPackets {

    //designate ports to be used here:
    static public final int tcpPort = 8080;
    static public final int udpPort = 8080;


    static public void register (EndPoint endPoint) { //EndPoint is either server or client
       Kryo obj = endPoint.getKryo(); //instead of writing the latter on every object to be registered.

       //follow this format of "obj.register(xxx.class)", where xxx is class.
        obj.register(FrameworkMessage.Ping.class); //ping isn't really used, is more for manual tests during developement.
        obj.register(Entry.class);
        obj.register(NewPlayer.class);
        obj.register(MovedRobot.class);
    }

    static public class Entry {
        public String name;

        public Entry() {}
        public Entry(String name) {
            this.name = name;
        }
    }

    static public class NewPlayer {
        public String name;
        public int playerID;
        public boolean joining;  //realized i needed a way to know if it was joining or leaving, check server-side

        public NewPlayer() {}
        public NewPlayer (String name, int playerID, boolean joining) {
            this.name = name;
            this.playerID = playerID;
            this.joining = joining;
        }
    }

    static public class MovedRobot { //should do for MVP
        public int playerID;
        public int keycode;

        public MovedRobot() {}
        public MovedRobot(int playerID, int keycode) {
            this.playerID = playerID;
            this.keycode = keycode;
        }
    }
    //implement the classes for use in RRClient/RRServer here

}
