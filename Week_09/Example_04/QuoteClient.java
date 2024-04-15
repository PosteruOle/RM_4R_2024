package Example_04;

import java.io.IOException;
import java.net.*;

public class QuoteClient {
    public static void main(String[] args) {
        try(DatagramSocket socket=new DatagramSocket()){
            byte[] buffer=new byte[1024];
            InetAddress host=InetAddress.getLocalHost();

            DatagramPacket request=new DatagramPacket(buffer, buffer.length, host, QuoteServer.PORT);
            socket.send(request);
            System.out.println("Request sent to server!");

            DatagramPacket response=new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            System.out.println("Response received from server!");

            String result=new String(buffer, 0, response.getLength());
            System.out.println("Quote of the moment: " + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
