import java.util.ArrayList;

/**
 * Created by kanghuang on 3/10/15.
 */
public class MapperIndex {
    class Node {

        String address;
        int port;
        Node (String address, int port){
            this.address = address;
            this.port = port;
        }
    }

    Node parent;
    ArrayList<Node> children;

    public MapperIndex(){

        children = new ArrayList<Node>();
    }

    public String getChildAddress(int i){
        Node child = this.children.get(i);
        return child.address + child.port;
    }

    public String getParentAddress(){
        return parent.address + parent.port;
    }

    public void setParentAddress(String address, int port){
        parent = createNode(address, port);
    }

    public void addChildAddress(String address, int port){
        children.add(createNode(address, port));
    }

    private Node createNode(String address, int port){
        return new Node(address, port);
    }

}
