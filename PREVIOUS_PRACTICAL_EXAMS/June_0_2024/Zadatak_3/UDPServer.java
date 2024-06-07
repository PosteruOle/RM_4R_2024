package June_0_2024.Zadatak_3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UDPServer {
    public static void main(String[] args) {
        HashMap<String, List<String>> map=new HashMap<>();
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("PREVIOUS_PRACTICAL_EXAMS/June_0_2024/Zadatak_3/studenti.txt")))){
            String line;
            while((line=reader.readLine())!=null){
                String[] items=line.split(" ");
                List<String> value=new ArrayList<>();
                value.add(items[1]);
                value.add(items[2]);
                value.add(items[3]);
                map.put(items[0], value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(
            DatagramSocket socket=new DatagramSocket(12321);
        ){
            byte[] buffer=new byte[512];
            DatagramPacket request=new DatagramPacket(buffer, buffer.length);

            socket.receive(request);
            System.out.println("Request received!");

            String message=new String(buffer, 0, request.getLength());
            String[] items=message.split(" ");
            String response;
            if(map.containsKey(items[0])){
                if(items[1].equalsIgnoreCase("ime")){
                    List<String> value=map.get(items[0]);
                    response=value.get(0) + " " + value.get(1);
                } else if(items[1].equalsIgnoreCase("prosek")){
                    List<String> value=map.get(items[0]);
                    response=value.get(2);
                } else {
                    response="Zahtev nije validan!";
                }
            } else {
                response="Zahtev nije validan!";
            }

            byte[] response_buffer=new byte[512];
            response_buffer=response.getBytes(StandardCharsets.UTF_8);
            DatagramPacket send=new DatagramPacket(response_buffer, response.length(), request.getAddress(), request.getPort());
            socket.send(send);
            System.out.println("Response sent!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
