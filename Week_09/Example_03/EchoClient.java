package Example_03;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
    public static final String hostname="localhost";
    public static void main(String[] args) {
        try(
            DatagramSocket socket=new DatagramSocket();
            Scanner scanner=new Scanner(System.in);
        ){
            byte[] buffer=new byte[1024];
            InetAddress host=InetAddress.getByName(hostname);
            String line;

            while(true) {
                line= scanner.nextLine();
                if(line.trim().equalsIgnoreCase("exit")){
                    break;
                }

                buffer=line.getBytes(StandardCharsets.UTF_8);
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, host, EchoServer.PORT);
                socket.send(request);
                //System.out.println("Request sent!");

                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                //System.out.println("Response received!");

                String result=new String(response.getData(), 0, response.getLength());
                System.out.println(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
