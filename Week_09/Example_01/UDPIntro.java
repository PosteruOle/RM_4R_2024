package Example_01;

import java.io.IOException;
import java.net.*;

public class UDPIntro {
    public static final String hostname="localhost";
    public static final int port=1234;
    public static void main(String[] args) {
        try(DatagramSocket socket=new DatagramSocket()){
            byte[] buffer=new byte[512];
            InetAddress host=InetAddress.getByName(hostname);

            DatagramPacket request=new DatagramPacket(buffer, buffer.length, host, port);
            DatagramPacket response=new DatagramPacket(buffer, buffer.length);

            System.out.println("Before send call!");

            socket.send(request);

            System.out.println("After send call!");

            socket.receive(response);

            System.out.println("After receive call!");

            byte[] responseData=response.getData();
            InetAddress responseAddress=response.getAddress();
            int responsePort=response.getPort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
