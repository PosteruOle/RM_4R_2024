package Example_01;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressIntro {
    public static void main(String[] args) {
        InetAddress address;
        Inet4Address ipv4address;
        Inet6Address ipv6address;
        try {
            System.out.println(InetAddress.getByName("www.google.com"));
            System.out.println(InetAddress.getByName("www.facebook.com"));
            System.out.println(InetAddress.getByName("www.v6.facebook.com"));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
