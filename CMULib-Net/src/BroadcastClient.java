import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 * Created by kanghuang on 2/24/15.
 */
public class BroadcastClient implements Runnable {
    byte[] buffer = new byte[65507];
    int port;
    DatagramPacket packet = null;
    String password = "This is for CMULib only";
    public BroadcastClient(int port){
        this.port = port;
    }
    @Override
    public void run() {
        try {
            recieve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void recieve() throws IOException {
        packet = new DatagramPacket(buffer, buffer.length);
        DatagramSocket ds = null;
        ds = new DatagramSocket(this.port);


        while(true){
            assert ds != null;
            ds.receive(packet);
            String s = new String(packet.getData(), 0, packet.getLength());
            if (s.equals(password)){
                reply();
            }
        }
    }
    class SlaveInfo implements Serializable{

    }
    void reply() throws IOException {
        Socket local = new Socket(packet.getAddress(), 8888);
        System.out.println(packet.getAddress());
        ObjectOutputStream out = new ObjectOutputStream(local.getOutputStream());
        SlaveInfo slaveInfo = new SlaveInfo();
        out.writeObject(slaveInfo);
    }
}
