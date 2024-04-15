package Example_02;

import java.io.IOException;
import java.net.*;

public class DayTimeClient {
    public static final String hostname="localhost";
    public static void main(String[] args) {
        try(DatagramSocket socket=new DatagramSocket()){
            byte[] buffer=new byte[512];
            //InetAddress host=InetAddress.getByName(hostname);
            InetAddress host=InetAddress.getLocalHost();

            DatagramPacket request=new DatagramPacket(buffer, buffer.length, host, DayTimeServer.DEFAULT_PORT);
            socket.send(request);
            System.out.println("Request sent to server!");

            DatagramPacket response=new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            System.out.println("Response received from server!");

            String result1=new String(response.getData(), 0, response.getLength());
            String result2=new String(buffer, 0, response.getLength());
            System.out.println(result1);
            System.out.println(result2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
