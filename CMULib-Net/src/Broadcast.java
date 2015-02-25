import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Broadcast {
    public static final int DEFAULT_PORT = 9000;
    private DatagramSocket socket;
    private DatagramPacket packet;

    public void broadcast() {
        try {
            socket = new DatagramSocket(DEFAULT_PORT);

        }catch (Exception ex) {
            System.out.println("Wrong!");
        }
        packet = new DatagramPacket (new byte[1], 1);
        
        int i = 3;
        while (i-- > 0)
        {
            try
            {
                byte[] outBuffer = "This is for CMULib only".toString ().getBytes ();
                packet.setData (outBuffer);
                packet.setLength (outBuffer.length);
                InetAddress address = InetAddress.getByName("255.255.255.255");
                socket.setBroadcast(true);
                packet.setAddress(address);
                socket.send (packet);
            }
            catch (IOException ie)
            {
                ie.printStackTrace();
            }
        }
    }


}
