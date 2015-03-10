/**
 * Created by kanghuang on 3/10/15.
 */
public class MapperMain {
    public static void main(String[] args){
        if(args[0].equals("gmaster")){
            MapperMaster master = new MapperMaster();
            master.startService();
        }else if(args[0].equals("lmaster")){
            MapperLocalMaster localMaster = new MapperLocalMaster();
        }else if(args[0].equals("slave")){
            MapperSlave slave = new MapperSlave();
            slave.StartService();
        }else{
            System.err.println("wrong arguments");
            System.exit(1);
        }
    }
}
