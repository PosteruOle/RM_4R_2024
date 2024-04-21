package Example_02;

import Example_03.SourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        ChatClient client=new ChatClient("localhost", ChatServer.SERVER_TEST_PORT);
        client.execute();
    }

    private String hostname;
    private int port;
    private String name;

    public ChatClient(String hostname, int port){
        this.hostname=hostname;
        this.port=port;
    }

    private void execute(){
        try{
            this.setName();

            Socket socket=new Socket(hostname, port);
            System.out.println("Connected to " + hostname);

            new ClientReadThread(this.name, socket).start();
            new ClientWriteThread(this.name, socket).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setName() throws IOException {
        System.out.println("Enter your name: ");
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        this.name= reader.readLine();
    }
}
