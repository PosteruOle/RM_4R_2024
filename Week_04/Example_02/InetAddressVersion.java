package Example_02;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

final class InetAddressVersion {

    public static void main(String[] args) {
        try {
            InetAddress addressv4 = InetAddress.getByName("google.com");
            System.out.println(addressv4.getHostAddress());
            printAddress(addressv4);
            System.out.println("IPv" + getVersion(addressv4));
            System.out.println();

            InetAddress addressv6 = InetAddress.getByName("ipv6.google.com");
            System.out.println(addressv6.getHostAddress());
            printAddress(addressv6);
            System.out.println("IPv" + getVersion(addressv6));
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    private static int getVersion(InetAddress addr) {
        byte[] address = addr.getAddress();

        // IPv4 has 4 bytes, IPv6 has 16 bytes
        /*
        switch (address.length) {
            case 4: return 4;
            case 16: return 6;
            default: return -1;
        }
        */

		if (addr instanceof Inet4Address)
			return 4;
		else if (addr instanceof Inet6Address)
			return 6;
		else
			return -1;

    }

    private static void printAddress(InetAddress addr) {
        byte[] address = addr.getAddress();
        System.out.print("IP address bytes: ");

        for (byte b : address) {
            int unsignedByte = b < 0 ? b + 256 : b;
            System.out.print(unsignedByte + " ");
        }

        System.out.println();
    }
}

