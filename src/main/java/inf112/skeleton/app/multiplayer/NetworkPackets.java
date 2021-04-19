package inf112.skeleton.app.multiplayer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.FrameworkMessage;

public class NetworkPackets {

    static public final int tcpPort = 8080;
    static public final int udpPort = 8080;


    static public void register (EndPoint endPoint) { //EndPoint is either server og client
       Kryo obj = endPoint.getKryo(); //instead of writing the latter on every object to be registered.

       //follow this format of "obj.register(xxx.class)", where xxx is class.
        obj.register(FrameworkMessage.Ping.class);
        obj.register(Entry.class);
        obj.register(NewPlayer.class);
        obj.register(MovedRobot.class);
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
        public int playerID;
        public boolean joining;  //realized i needed a way to know if it was joining or leaving, check server-side

        public NewPlayer() {}
        public NewPlayer (String name, int playerID, boolean joining) {
            this.name = name;
            this.playerID = playerID;
            this.joining = joining;
        }
    }

    static public class MovedRobot {
        public int playerID;
        public int keycode;
        //public float x;
        //public float y;
        //public float xD;
        //public float yD;

        public MovedRobot() {}
        public MovedRobot(int playerID, int keycode) {
            this.playerID = playerID;
            this.keycode = keycode;
        }
        /**
        public MovedRobot (int playerID, float x, float y, float xD, float yD) {
            this.playerID = playerID;
            this.x = x;
            this.y = y;
            this.xD = xD;
            this.yD = yD;
        }
*/
    }
    //implement the classes for use in RRClient/RRServer here

}
