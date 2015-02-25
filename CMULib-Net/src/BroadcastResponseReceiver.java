import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by zhoucheng on 2/24/15.
 */
public class BroadcastResponseReceiver implements Runnable {
  private Socket client;

  public BroadcastResponseReceiver(Socket socket) {
    this.client = socket;
  }

  public void run() {
    try {
      ObjectInputStream in = new ObjectInputStream(client.getInputStream());
      DataOutputStream out = new DataOutputStream(client.getOutputStream());

      Object obj = in.readObject();
      SlaveInfo slave = null;

      if (obj instanceof SlaveInfo) {
        slave = (SlaveInfo) obj;
        System.out.println("Used Memory: " + slave.mUsedMemory);
        out.writeBoolean(true);
      } else {
        out.writeBoolean(false);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}