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
            socket = new DatagramSocket();

        }catch (Exception ex) {
            System.out.println("Wrong!");
        }
        packet = new DatagramPacket (new byte[1], 1);
        
        int i = 1;
        while (i-- > 0)
        {
            try
            {
                byte[] outBuffer = "This is for CMULib only".toString ().getBytes ();
                //socket.setBroadcast(true);
                DatagramPacket sendPacket = new DatagramPacket(outBuffer,
                            outBuffer.length,
                            InetAddress.getByName("255.255.255.255"), 9000);
                socket.send (sendPacket);
            }
            catch (IOException ie)
            {
                ie.printStackTrace();
            }
        }
    }


}
