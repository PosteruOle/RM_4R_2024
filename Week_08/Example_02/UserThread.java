package Example_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread extends Thread {
    private final Socket socket;
    private final ChatServer chatServer;
    private String name;
    private PrintWriter toUser;

    public UserThread(Socket socket, ChatServer chatServer){
        this.socket=socket;
        this.chatServer=chatServer;
    }

    @Override
    public void run() {
         try{
             this.toUser=new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromUser=new BufferedReader(new InputStreamReader(socket.getInputStream()));

             this.name=fromUser.readLine();
             this.sendMessage("Connected users: " + this.chatServer.getUserNames());

             this.chatServer.broadcast("New connected user: " + this.name, this);

             String clientMesssage;
             while(true){
                 clientMesssage=fromUser.readLine().trim();
                 if(clientMesssage.equalsIgnoreCase("Bye"))
                     break;
                 this.chatServer.broadcast("[" + this.name + "]: " + clientMesssage, this);
             }

             this.chatServer.remove(this);
             this.socket.close();
             this.chatServer.broadcast(this.name + " has left the chat!", this);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public void sendMessage(String s) {
        if(this.toUser!=null){
            this.toUser.println(s);
        }
    }

    public String getNickName(){
        return this.name;
    }
}
