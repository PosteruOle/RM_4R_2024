package June_0_2024.Zadatak_3;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try(
            DatagramSocket socket=new DatagramSocket();
            Scanner scanner=new Scanner(System.in);
        ) {
            String host="localhost";
            int port=12321;
            System.out.println("Unesite indeks studenta, a zatim ime ili prosek opciju:");
            String line=scanner.nextLine();

            byte[] buffer=new byte[512];
            buffer=line.getBytes(StandardCharsets.UTF_8);
            DatagramPacket send=new DatagramPacket(buffer, line.length(), InetAddress.getByName(host), port);
            socket.send(send);

            byte[] receive_buffer=new byte[512];
            DatagramPacket receive=new DatagramPacket(receive_buffer, receive_buffer.length);
            socket.receive(receive);

            String response=new String(receive_buffer, 0, receive.getLength());
            System.out.println(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
