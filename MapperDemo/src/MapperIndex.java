import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by kanghuang on 3/10/15.
 */
public class MapperIndex {
    class Node {

        InetAddress address;
        int port;

    }

    Node parent;
    ArrayList<Node> children;

    public MapperIndex(){
        parent = new Node();
        children = new ArrayList<Node>();
    }

    public String getChildAddress(int i){
        Node child = this.children.get(i);
        return child.address.getHostAddress() + child.port;
    }

    public String getParentAddress(){
        return parent.address.getHostAddress() + parent.port;
    }

    

}
