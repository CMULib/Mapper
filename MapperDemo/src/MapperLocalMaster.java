import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by kanghuang on 3/10/15.
 */
public class MapperLocalMaster {
    MapperIndex index = new MapperIndex();
    ServerSocket server;

    public MapperLocalMaster(int port){
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("server post is disabled ...");
            e.printStackTrace();
        }
    }

    public MapperLocalMaster(){
        try {
            server = new ServerSocket(11183);
        } catch (IOException e) {
            System.err.println("server post is disabled ...");
            e.printStackTrace();
        }
    }

}
