import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class BroadcastManager {
  private static final BroadcastManager INSTANCE = new BroadcastManager();

  private ConcurrentHashMap<Long, SlaveInfo> slavePool;

  private ConcurrentHashMap<SlaveInfo, Long> idPool;

  private static long slaveSeqNumber;

  private BroadcastManager() {
    slavePool = new ConcurrentHashMap<>();
    idPool = new ConcurrentHashMap<>();
    slaveSeqNumber = -1;
  }

  public static BroadcastManager getInstance() {
    return INSTANCE;
  }

  public void removeSlave(SlaveInfo processInstance) {
    long id = idPool.get(processInstance);
    idPool.remove(processInstance);
    slavePool.remove(id);
  }

  /*
   * Generate Process ID This is a thread-safe method.
   */
  private static synchronized long nextProcessID() {
    return ++slaveSeqNumber;
  }

  public void startServer() {
    Thread t = new Thread(new BroadcastServer()); // use default 8888 port
    t.start();
  }

  public void startConsole() {
    System.out.println("Type in command:");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      String commandLine = null;
      try {
        commandLine = reader.readLine();
      } catch (IOException e) {
        System.err.println("Cannot input empty command");
        System.exit(-1);
      }
      commandLineParsing(commandLine);
    }
  }

  private void commandLineParsing(String commandLine) {
    if (commandLine == null || commandLine.length() == 0)
      return;

    String[] args = commandLine.split("\\s+");
    if (args == null || args.length == 0) {
      return;
    }

    switch (args[0].toLowerCase()) {
      case "ls":
        commandLS();
        break;
      case "c":
        commandCheck(args);
        break;
      case "quit":
        commandQuit();
        break;
      default:
        System.err.println("Unknown command: " + commandLine);
    }
  }

  private void commandLS() {
    ArrayList<Long> idPool = new ArrayList<Long>(slavePool.keySet());
    if (idPool == null || idPool.size() == 0) {
      System.out.println("No running process");
      return;
    }

    Collections.sort(idPool);
    for (Long id : idPool)
      System.out.println("ID=" + id + "\t" + slavePool.get(id).toString());
  }

  private void commandCheck(String[] args) {
    if (args.length <= 1) {
      System.out.println("Usage: c slaveID1 slaveID2...");
      return;
    }
    for (int i = 1; i < args.length; i++) {
      try {
        System.out.println(slavePool.get(Long.parseLong(args[i])).toString());
      } catch (Exception e) {        // TODO Auto-generated catch block
        System.err.println("Failed to find slave which ID = " + args[i]);
      }
    }
  }

  private void migrate(Socket socket, MigratableProcess process, String hostIP) throws IOException {
    // TODO Auto-generated method stub
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    DataInputStream in = new DataInputStream(socket.getInputStream());
    boolean successful = false;
    try {
      out.writeObject(process);
      successful = in.readBoolean();
    } catch (IOException e) {
      System.err.println("Failed to send information to " + hostIP + " due to IOException");
    } finally {
      if (!successful)
        System.out.println("Ready to restart sending in current server...");
      out.close();
      in.close();
      socket.close();
    }
  }

  private void commandQuit() {
    System.out.println("Quit...");
    System.exit(0);
  }

  public static void main(String[] args) {
    BroadcastManager.getInstance().startServer();
    BroadcastManager.getInstance().startConsole();
  }
}