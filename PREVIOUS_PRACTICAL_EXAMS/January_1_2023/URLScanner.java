package January_1_2023;

import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class URLScanner {
    public static void main(String[] args) {
        try(Scanner scanner=new Scanner(System.in)){
            String line;
            while(true){
                if((line = scanner.nextLine())==null){
                    break;
                }
                try{
                    URL url=new URL(line);

                    if(isIpAddress(url.getHost())){
                        int version=getVersion(url.getHost());
                        System.out.print("(v" + version + ") ");
                        System.out.print(url.getProtocol() + " ");
                        System.out.print(url.getPath() + " ");
                        if(version==4)
                            System.out.println(Arrays.toString(getBytes(url.getHost())));
                        else
                            System.out.println();
                    } else {
                        System.out.println(url.getProtocol() + " " + url.getAuthority() + " " + url.getPath());
                    }

                } catch (MalformedURLException e) {
                    System.out.println("Uneti URL nije validan!");
                }
            }
        }
    }

    private static int[] getBytes(String host) {
        try{
            InetAddress hostAddress=InetAddress.getByName(host);
            byte[] bytes=hostAddress.getAddress();
            int[] result=new int[bytes.length];
            for(int i=0;i<bytes.length;i++){
                if(bytes[i]<0)
                    result[i]=bytes[i]+256;
                else
                    result[i]=bytes[i];
            }
            return result;
        } catch (UnknownHostException e) {
            // Ignore!
        }

        return new int[4];
    }

    private static boolean isIpAddress(String authority) {
        return authority.chars().allMatch(c->c=='.' || Character.isDigit(c));
    }

    private static int getVersion(String host){
        try {
            InetAddress hostAddress=InetAddress.getByName(host);
            if(hostAddress instanceof Inet4Address)
                return 4;
            else if(hostAddress instanceof Inet6Address)
                return 6;
            else
                return -1;
        } catch (UnknownHostException e) {
            //Ignore!
        }
        return -1;
    }
}
