package June_0_2024.Zadatak_2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try(
            Socket socket=new Socket("localhost", 12345);
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner=new Scanner(System.in);
        ){
            String line;
            while((line= reader.readLine())!=null){
                if(line.equalsIgnoreCase("end")){
                    break;
                }
                System.out.println(line);
            }

            System.out.println("Enter the name of the source city: ");
            String city=scanner.nextLine();

            writer.write(city);
            writer.newLine();
            writer.flush();

            while((line= reader.readLine())!=null){
                if(line.equalsIgnoreCase("end")){
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
