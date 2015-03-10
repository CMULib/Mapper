import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
            server = new ServerSocket(MapperUtil.localMasterPort);
        } catch (IOException e) {
            System.err.println("server post is disabled ...");
            e.printStackTrace();
        }
    }

    public void StartService() throws IOException {
        int counter = 0;
        StringBuilder reply = new StringBuilder();
        while(counter < 3){
            Socket sk = server.accept();
            counter++;
            DataInputStream input = (DataInputStream) sk.getInputStream();
            reply.append(input.readUTF());
        }
        String address[] = index.getParentAddress().split("/");
        Socket tcp = new Socket(address[0], Integer.parseInt(address[1]));
        DataOutputStream output = new DataOutputStream(tcp.getOutputStream());
        output.writeUTF(reply.toString());
    }

}
