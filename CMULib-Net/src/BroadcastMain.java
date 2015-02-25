/**
 * Created by kanghuang on 2/24/15.
 */
public class BroadcastMain {
    public static void main(String[] args){
        if (args[0].equals("master")){
            Broadcast tool = new Broadcast();
            BroadcastServer server = new BroadcastServer();
            new Thread(server).start();
           // BroadcastManager keeper = BroadcastManager.getInstance();
        }
        else if (args[0].equals("slave")){
            BroadcastClient client = new BroadcastClient(9000);
            new Thread(client).start();
        }
    }
}
