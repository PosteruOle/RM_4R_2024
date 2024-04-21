package Example_02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteThread extends Thread{
    private String name;
    private PrintWriter toServer;

    ClientWriteThread(String name, Socket socket){
        this.name=name;
        try{
            toServer=new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        toServer.println(this.name);
        try(Scanner scanner=new Scanner(System.in)){
            String text;
            while(true){
                System.out.printf("\r[%s]: ", this.name);
                text=scanner.nextLine().trim();

                toServer.println(text);
                if(text.equalsIgnoreCase("Bye")){
                    break;
                }
            }
            System.out.println("Disconnected!");
        }
    }
}
