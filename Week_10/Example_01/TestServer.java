package Example_01;

import Example_04.ClientHandlerRunnable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TestServer {
    public static final int DEFAULT_PORT=12345;
    public static void main(String[] args) {
        try(ServerSocket server=new ServerSocket(DEFAULT_PORT)){
            while(true){
                System.out.println("Listening for clients...");

                Socket client=server.accept();
                System.out.println("Client accepted!");

                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                writer.write("Hello there!");
                writer.newLine();
                writer.flush();

                writer.write("Now you can handle a finger protocol!");
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
