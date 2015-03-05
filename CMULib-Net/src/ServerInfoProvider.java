import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * This is an abstract class which can provide current available hosts for the client
 * It can help the design for mapping a optimal cluster dynamically.
 * 
 *
 */
public abstract class ServerInfoProvider {
    
    private Collection<InetSocketAddress> addresses;
    
    /**
     * return current size of the addresses 
     * @return
     */
    public synchronized int size() {
	return addresses.size();
    }
    
    //constructor
    public ServerInfoProvider (Collection<InetSocketAddress> addresses) {
	this.addresses = addresses;
    }
    
    /**
     * get the next address of available servers for the client to connect
     * 
     * @return
     */
    public abstract InetSocketAddress getNext();
    
    /**
     *  callback function when collected successfully
     */
    public abstract void onConnected();
    
    /**
     * update the list of available server addresses
     * @param addresses
     * @param currentHost
     * @return
     */
    public abstract boolean updateAddresses(Collection<InetSocketAddress> addresses,
	        InetSocketAddress currentHost);

}
