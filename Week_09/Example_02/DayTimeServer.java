package Example_02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class DayTimeServer {
    public static final int DEFAULT_PORT=1234;
    public static void main(String[] args) {
        try(DatagramSocket socket=new DatagramSocket(DEFAULT_PORT)){
            byte[] buffer=new byte[512];
            while(true){
                System.out.println("Server is active on " + DEFAULT_PORT + " port...");

                DatagramPacket request=new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                System.out.println("Request received from client!");

                buffer=new Date().toString().getBytes(StandardCharsets.UTF_8);
                DatagramPacket response=new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                socket.send(response);
                System.out.println("Response sent to client!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
