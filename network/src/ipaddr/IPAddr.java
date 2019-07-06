package ipaddr;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddr {
	public static void main(String []args) {
		InetAddress ip;
		try {
			ip=InetAddress.getLocalHost();
			String name=ip.getHostName();
			String ipaddr=ip.getHostAddress();
			System.out.println("com_name:"+name);
			System.out.println("ipaddr:"+ipaddr);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	


}
