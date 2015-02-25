import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class BroadcastServer implements Runnable {
  private ServerSocket server; 
  
  private boolean running;
  
  public BroadcastServer(int port) {
    try {
      server = new ServerSocket(port);
      System.out.println("Starting server on port: " + server.getLocalPort());
      System.out.println("IP Address: " + server.getInetAddress());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.err.println("Failed to open port " + port);
      e.printStackTrace();
    }
  }
  
  public BroadcastServer() {
    // TODO Auto-generated constructor stub
    this(8888);
  }
  
  @Override
  public void run() {
    // TODO Auto-generated method stub
    running = true;
    while (running) {
      accept();
    }
  }
  
  private void accept() {
    Socket client = null;
    try {
      client = server.accept();
      new Thread(new BroadcastResponseReceiver(client)).start();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.err.println("Server acception error");
      e.printStackTrace();
    }
  }
}