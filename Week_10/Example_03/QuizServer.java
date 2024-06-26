package Example_03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer {
    static final Integer PORT=1337;
    static final String host="localhost";

    public static void main(String[] args) {
        try(ServerSocket server=new ServerSocket(PORT)){
            System.out.println("Server is now active and it is listening on port " + PORT + "...");
            while(true){
                Socket client=server.accept();

                System.out.println("--------------------------");
                System.out.println("Client accepted!");

                serve(client);

                System.out.println("Client handled!");
                System.out.println("--------------------------");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void serve(Socket client) {
        try(BufferedReader r=new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter w=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        ){
            String oblast=r.readLine();
            if(oblast.equalsIgnoreCase("Geografija")){
                String pitanje="Koji je glavni grad Francuske?";
                w.write(pitanje);
                w.newLine();
                w.flush();
            } else{
                String odgovor="Nema pitanja iz ove oblasti!";
                w.write(odgovor);
                w.newLine();
                w.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
