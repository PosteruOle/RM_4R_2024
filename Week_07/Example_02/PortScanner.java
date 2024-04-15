package Example_02;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class PortScanner {
    public static void main(String[] args) {
        System.out.println("Welcome to port scanner!");

        //String hostname="www.matf.bg.ac.rs";
        String hostname="localhost";

        System.out.println("Start time: " + new Date());

        for(int port=1;port<65536;port++){
            System.out.print("Testing port: " + port);

            try(Socket socket=new Socket(hostname, port)){
                System.out.println("\rSocket data: " + socket);
                System.out.println("Found at: " + new Date());
            } catch (UnknownHostException e) {
                e.printStackTrace();
                break;
            } catch (IOException e) {
                // Ignore!
            }
        }

        System.out.println("\rEnd time: " + new Date());
    }
}
