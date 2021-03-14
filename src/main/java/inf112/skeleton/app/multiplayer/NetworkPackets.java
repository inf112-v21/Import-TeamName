package inf112.skeleton.app.multiplayer;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkPackets {

    static public final int tcpPort = 24444;
    static public final int udpPort = 25555;


    static public void register (EndPoint endPoint) {
        Kryo obj = endPoint.getKryo(); //instead of writing the latter on every object to be registered.

        obj.register(Vector2.class); //follow this format of "obj.register(xxx.class)", where xxx is class.

    }

}
