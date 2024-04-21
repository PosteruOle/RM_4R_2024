package Example_02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChatServer {
    public static final int SERVER_TEST_PORT=12345;
    private int port;
    private Set<UserThread> users=new HashSet<>();

    public static void main(String[] args) {
        ChatServer server=new ChatServer(SERVER_TEST_PORT);
        server.execute();
    }

    public ChatServer(int port) {
        this.port=port;
    }

    private void execute() {
        try(ServerSocket serverSocket=new ServerSocket(port)){
            System.out.println("Server is listening on port " + port + "...");

            while(true){
                Socket client=serverSocket.accept();
                System.out.println("Client accepted!");

                UserThread user=new UserThread(client, this);
                this.users.add(user);

                user.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getUserNames() {
        return this.users.stream()
                        .map(UserThread::getNickName)
                        .collect(Collectors.toList());
    }

    public void broadcast(String s, UserThread except) {
        this.users.stream()
                .filter(u->u!=except)
                .forEach(u->u.sendMessage(s));
    }

    public void remove(UserThread userThread) {
        String userName=userThread.getNickName();
        this.users.remove(userThread);
        System.err.println("Client " + userName + " disconnected!");
    }
}
