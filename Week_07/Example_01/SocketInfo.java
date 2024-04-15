package Example_01;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketInfo {
    public static void main(String[] args) {
        try(Socket socket=new Socket("www.matf.bg.ac.rs", 80)){
            System.out.println(socket);
            System.out.println(socket.getInetAddress());
            System.out.println(socket.getPort());
            System.out.println(socket.getLocalPort());
            System.out.println(socket.getLocalAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
