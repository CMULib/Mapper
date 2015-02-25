import java.io.File;

public class Probe {
  public static class probe {
    long curTimeMs;

    int coreNum;

    long freeMemory;

    String maxMemory;

    long totalMemoryForJVM;

    String fileSystemRoot;

    String totalSpace;

    long freeSpace;

    long usableSpace;

    String osName;

    String osVersion;

    String osArch;

    probe() {
      coreNum = Runtime.getRuntime().availableProcessors();
      freeMemory = Runtime.getRuntime().freeMemory();
      maxMemory = Runtime.getRuntime().maxMemory() == Long.MAX_VALUE ? "no limit" : maxMemory;
      totalMemoryForJVM = Runtime.getRuntime().totalMemory();
      File[] roots = File.listRoots();
      for (File root : roots) {
        fileSystemRoot = root.getAbsolutePath();
        totalSpace = root.getAbsolutePath();
        freeSpace = root.getTotalSpace();
        usableSpace = root.getUsableSpace();
      }

      String nameOS = "os.name";
      String versionOS = "os.version";
      String architectureOS = "os.arch";

      osName = System.getProperty(nameOS);
      osVersion = System.getProperty(versionOS);
      osArch = System.getProperty(architectureOS);

      curTimeMs = System.currentTimeMillis();
    }

    probe update() {
      return new probe();
    }

    void print() {
      long curTimeMs = System.currentTimeMillis();
      System.out.println("curTimeMs: " + curTimeMs + " ms");

      /* Total number of processors or cores available to the JVM */
      System.out.println("Available processors (cores): "
              + Runtime.getRuntime().availableProcessors());

      /* Total amount of free memory available to the JVM */
      System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());

      /* This will return Long.MAX_VALUE if there is no preset limit */
      long maxMemory = Runtime.getRuntime().maxMemory();
      /* Maximum amount of memory the JVM will attempt to use */
      System.out.println("Maximum memory (bytes): "
              + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

      /* Total memory currently available to the JVM */
      System.out.println("Total memory available to JVM (bytes): "
              + Runtime.getRuntime().totalMemory());

      /* Get a list of all filesystem roots on this system */
      File[] roots = File.listRoots();

      /* For each filesystem root, print some info */
      for (File root : roots) {
        System.out.println("File system root: " + root.getAbsolutePath());
        System.out.println("Total space (bytes): " + root.getTotalSpace());
        System.out.println("Free space (bytes): " + root.getFreeSpace());
        System.out.println("Usable space (bytes): " + root.getUsableSpace());
      }

      String nameOS = "os.name";
      String versionOS = "os.version";
      String architectureOS = "os.arch";
      System.out.println("\n  The information about OS");
      System.out.println("\nName of the OS: " + System.getProperty(nameOS));
      System.out.println("Version of the OS: " + System.getProperty(versionOS));
      System.out.println("Architecture of THe OS: " + System.getProperty(architectureOS));
    }
  }

  public static void main(String[] args) {
    probe p = new probe();
    p.print();
    p = p.update();
    p.print();
  }
}
