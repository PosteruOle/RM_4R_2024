package Example_03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {
    public static final int PORT=4321;
    public static void main(String[] args) {
        try(DatagramSocket socket=new DatagramSocket(PORT)){
            byte[] buffer=new byte[1024];
            while(true){
                System.out.println("Server is active on " + PORT + " port...");

                DatagramPacket request=new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                System.out.println("Request received!");

                DatagramPacket response=new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                socket.send(response);
                System.out.println("Response sent!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
