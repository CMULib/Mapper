import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class broadcast {
    public static final int DEFAULT_PORT = 8000;
    private DatagramSocket socket;
    private DatagramPacket packet;

    public void connect() {
        try {
            socket = new DatagramSocket(DEFAULT_PORT);

        }catch (Exception ex) {
            System.out.println("Wrong!");
        }
        packet = new DatagramPacket (new byte[1], 1);
        
        while (true)
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
