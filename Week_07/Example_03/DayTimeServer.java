package Example_03;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer {
    public static final int DEFAULT_PORT=8765;
    public static void main(String[] args) {
        try(ServerSocket server=new ServerSocket(DEFAULT_PORT)){
            while(true){
                System.out.println("Listening for clients...");

                Socket client=server.accept();
                System.out.println("Client accepted!");

                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                Date now=new Date();

                writer.write(now.toString());
                writer.newLine();
                writer.flush();

                System.out.println("Client handled!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
