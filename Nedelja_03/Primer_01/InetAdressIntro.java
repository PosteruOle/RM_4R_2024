package Primer_01;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAdressIntro {
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        // InetAddress represents an IPv4 (32b) or IPv4 (128b) address
        InetAddress address;
        Inet4Address ipv4addr;
        Inet6Address ipv6addr;

        // IPv6 addresses might not work for you, it all depends of your provider and
        // whether you have the IPv6 support

        try {
            System.out.println(InetAddress.getByName("www.google.com"));
            System.out.println(InetAddress.getByName("www.facebook.com"));
            System.out.println(InetAddress.getByName("www.v6.facebook.com"));
            System.out.println(Arrays.toString(InetAddress.getAllByName("www.v6.facebook.com")));
            System.out.println(Arrays.toString(InetAddress.getAllByName("google.com")));
            System.out.println(InetAddress.getByName("208.201.239.101"));
            System.out.println();

            System.out.println(InetAddress.getLocalHost());

            // Beware of negative bytes in byte[] address array!
            // We will see how to convert these later
            System.out.println(InetAddress.getByName("www.math.rs"));
            System.out.println(Arrays.toString(InetAddress.getByName("www.math.rs").getAddress()));

            // We shouldn't do this
            try {
                ipv4addr = (Inet4Address) InetAddress.getByName("ipv6.google.com");
            } catch (ClassCastException ex) {
                System.out.println("Cast failed!");
            }

            ipv6addr = (Inet6Address) InetAddress.getByName("ipv6.google.com");
            System.out.println(ipv6addr.getHostName());
            System.out.println(ipv6addr.getCanonicalHostName());
            System.out.println(ipv6addr.getHostAddress());
            System.out.println();

            InetAddress matfShort = InetAddress.getByName("www.math.rs");
            InetAddress matfFull = InetAddress.getByName("www.matf.bg.ac.rs");
            System.out.println(matfShort);
            System.out.println(matfFull);
            if (matfShort.equals(matfFull))
                System.out.println("www.math.rs is the same as www.matf.bg.ac.rs");
            else
                System.out.println("www.math.rs is not the same as www.matf.bg.ac.rs");

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}
