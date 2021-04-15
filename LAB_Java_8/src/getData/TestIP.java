package getData;

import java.net.InetAddress;
import java.net.UnknownHostException;

final public class TestIP {
    InetAddress currentIP = null;

    public void getIP(String IP) {
        try {
            currentIP = InetAddress.getByName(IP);
            System.out.println(IP + ": " + currentIP.getHostAddress());
            System.out.println("Local" + ": " + InetAddress.getLocalHost());
        } catch (
                UnknownHostException e) {
            System.err.println("адрес недоступен " + e);
        }
    }
}
