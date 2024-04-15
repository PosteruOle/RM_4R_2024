package Example_04;

import java.io.*;
import java.net.Socket;

public class ClientHandlerRunnable implements Runnable{
    private Socket client;
    ClientHandlerRunnable(Socket client){
        this.client=client;
    }
    @Override
    public void run() {
        try(
            BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        ){
            String line;

            while((line=reader.readLine())!=null){
                writer.write(line);
                writer.newLine();
                writer.flush();

            }

            System.out.println("Client handled from Thread with " + Thread.currentThread().threadId() + "!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
