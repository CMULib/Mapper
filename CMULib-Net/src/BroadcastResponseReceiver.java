import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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