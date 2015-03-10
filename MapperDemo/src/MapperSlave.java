import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

/**
 * Created by kanghuang on 3/10/15.
 */
public class MapperSlave {

    MapperIndex index = new MapperIndex();

    public MapperSlave(){
        index.setParentAddress(MapperUtil.LocalMasterIP, MapperUtil.localMasterPort);
    }

    public MapperSlave(String masterAddress, int port){
        index.setParentAddress(masterAddress, port);
    }

    public void StartService() {
        try {
            Ping();
        } catch (IOException e) {
            System.err.println("slave communication error!");
            e.printStackTrace();
        }
    }

    private void Ping() throws IOException {
        String address[] = index.getParentAddress().split("/");
        Socket tcp = new Socket(address[0], Integer.parseInt(address[1]));
        DataOutputStream output = new DataOutputStream(tcp.getOutputStream());
        output.writeUTF("slave in " + InetAddress.getLocalHost() + new Random().nextFloat());
    }
}
