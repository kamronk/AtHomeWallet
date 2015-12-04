package main;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import network.ParseRoute;

import org.apache.commons.net.util.SubnetUtils;

/**
 *
 * @author KamronK
 */
public class NetworkInterfacer {
    public static void main(String[] args){
    	NetworkInterfacer pr = new NetworkInterfacer();
        System.out.println( "Address: " + pr.getAddress());
        System.out.println( "Netmask: " + pr.getNetmask());
        System.out.println( "Network: " + pr.getNetwork());
        System.out.println( "Broadcast: " + pr.getBroadcast());
        System.out.println( "Gateway: " + pr.getGateway());
    }

    private String address;//
    private String netmask = "255.255.255.0";
    private String network;
    private String broadcast;//
    private String gateway;//
    
    public NetworkInterfacer(){
        ParseRoute pr = ParseRoute.getInstance();
        this.address = pr.getLocalIPAddress();
        this.gateway = pr.getGateway();

		String[] temp = this.address.split("\\.");
		this.network = temp[0] + "." + temp[1] + "." + temp[2] + ".0";
		this.broadcast = temp[0] + "." + temp[1] + "." + temp[2] + ".255";
    }

	public String getAddress() {
		return address;
	}

	public String getNetmask() {
		return netmask;
	}

	public String getNetwork() {
		return network;
	}

	public String getBroadcast() {
		return broadcast;
	}

	public String getGateway() {
		return gateway;
	}
    
    
    
}
