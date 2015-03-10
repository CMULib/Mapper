import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by kanghuang on 3/10/15.
 */
public class MapperMaster {

    private MapperIndex index;

    public MapperMaster(){
        index = new MapperIndex();
    }

    public void startService(){
        ListenerService listener = new ListenerService(MapperUtil.gmaster);
        listener.start();
    }

    private class ListenerService extends Thread{
        ServerSocket listener = null;

        public ListenerService(int port){
            try {
                listener = new ServerSocket(port);
            } catch (IOException e) {
                //System.out.println("listener socket fails");
                //logger.error("listener socket fails");
                e.printStackTrace();
            }
        }

        /**
         * run function, inherited from Thread Class
         */
        public void run() {
            int i = 0;
            while(true) {
                try {
                    Socket sock = listener.accept();
                    //System.out.println("New slave connected");
                    //  logger.info("New slave connected");
                    index.addChild(sock.getInetAddress(), sock.getPort());
                    System.out.println( index.getChildAddress(i++));
                    if(i == 3){
                        System.out.println("finish!");
                        System.exit(0);
                    }
                }catch (IOException e){
                    System.err.println("fail to establish a socket with a slave node");
                    e.printStackTrace();
                }
            }
        }

        private long getCurrentTimeInMillionSeconds(){
            Date currentData = new Date();
            return currentData.getTime();
        }

    }
}
