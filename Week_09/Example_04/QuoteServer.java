package Example_04;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class QuoteServer {
    public static final int PORT=1212;
    public static void main(String[] args) {
        try(
            DatagramSocket socket=new DatagramSocket(PORT);
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("Week_07/Example_04/one_liners.txt")));
        ){
            byte[] buffer=new byte[1024];
            String line;

            while(true){
                DatagramPacket request=new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                System.out.println("Received!");

                if((line= reader.readLine())!=null){
                    buffer=line.getBytes(StandardCharsets.UTF_8);
                } else {
                    buffer=new Date().toString().getBytes(StandardCharsets.UTF_8);
                }

                DatagramPacket response=new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                socket.send(response);
                System.out.println("Sent!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
