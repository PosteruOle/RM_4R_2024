package Example_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DayTimeClient {
    public static void main(String[] args) {
        String hostname="localhost";

        try(Socket socket=new Socket(hostname, DayTimeServer.DEFAULT_PORT)){
            System.out.println("Connected!");

            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String time=reader.readLine().trim();

            System.out.println("It is " + time + " at " + hostname + " server!");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Disconnected!");
    }
}
