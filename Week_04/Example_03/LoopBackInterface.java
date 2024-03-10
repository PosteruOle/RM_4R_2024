package Example_03;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

final class LoopBackInterface {

    // The loopback device is a special, virtual network interface that
    // your computer uses to communicate with itself. It is used mainly
    // for diagnostics and troubleshooting, and to connect to servers
    // running on the local machine.

    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getLoopbackAddress();
            NetworkInterface ni = NetworkInterface.getByInetAddress(local);

            if (ni == null) {
                System.err.println("No local loopback address.");
                return;
            }

            System.out.println(ni);
            System.out.printf("%s\t%s\n%s\n", ni.getName(), ni.getDisplayName(), ni.getIndex());

        } catch (SocketException ex) {
            System.err.println("Could not list sockets.");
        }
    }
}

