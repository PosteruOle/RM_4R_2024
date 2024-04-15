package Example_04;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) {
        try(
            Socket socket=new Socket("localhost", EchoServer.DEFAULT_PORT);
            BufferedReader networkInput=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter networkOutput=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
        ){
            System.out.println("Connected!");

            while(true){
                String line=userInput.readLine().trim();
                if(line.equalsIgnoreCase("exit")){
                    break;
                }

                networkOutput.write(line);
                networkOutput.newLine();
                networkOutput.flush();

                System.out.println(networkInput.readLine());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Disconnected!");
    }
}
