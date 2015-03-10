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
            while(true) {
                try {
                    Socket sock = listener.accept();
                    //System.out.println("New slave connected");
                    //  logger.info("New slave connected");
//                    SDSlave aSlave = new SDSlave(sock.getInetAddress(), sock.getPort());
//                    aSlave.setReader(new BufferedReader(new InputStreamReader(sock.getInputStream())));
//                    aSlave.setWriter(new PrintWriter(sock.getOutputStream()));
//                    int key = (int)(getCurrentTimeInMillionSeconds() % 1000000);
//                    synchronized (slaveHashMap){
//                        //TODO: write a robust slave id assignment function
//                        slaveHashMap.put(key, aSlave);
//                    }
                }catch (IOException e){
                    //System.err.println("fail to establish a socket with a slave node");
                    // logger.error("fail to establish a socket with a slave node");
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
