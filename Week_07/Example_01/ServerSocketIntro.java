package Example_01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketIntro {
    public static final int DEFAULT_PORT=1234;
    public static void main(String[] args) {
        // 1st solution
        try(ServerSocket serverSocket=new ServerSocket(DEFAULT_PORT)){
            System.out.println("Listening for clients...");

            Socket client =serverSocket.accept();
            System.out.println("Client accepted!");

            serve(client);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2nd solution
        try(ServerSocket serverSocket=new ServerSocket()){
            serverSocket.bind(new InetSocketAddress(DEFAULT_PORT));

            while(true){
                System.out.println("Listening for clients...");

                Socket client =serverSocket.accept();
                System.out.println("Client accepted!");

                serve(client);

                System.out.println("Client handled!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void serve(Socket client) {
        // Serve client!
    }
}
