package Example_01;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketIntro {
    public static void main(String[] args) {
        try(Socket socket=new Socket("localhost", ServerSocketIntro.DEFAULT_PORT)){

            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Program's logic...

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
