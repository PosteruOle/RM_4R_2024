package June_0_2024.Zadatak_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TCPServer {
    public static void main(String[] args) {
        HashMap<String, List<String>> map=new HashMap<>();
        try(DirectoryStream<Path> directoryStream= Files.newDirectoryStream(Paths.get("PREVIOUS_PRACTICAL_EXAMS/June_0_2024/aerodromi"))){
            for(Path path: directoryStream){
                BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(path))));
                String fileName=path.toString().substring(path.toString().lastIndexOf('/')+1, path.toString().lastIndexOf('.'));
                map.put(fileName, new ArrayList<>());

                String line;
                List<String> value=map.get(fileName);
                while((line=reader.readLine())!=null){
                    value.add(line);
                }
                map.put(fileName, value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(ServerSocket serverSocket=new ServerSocket(12345)){
            while(true){
                System.out.println("The server is active and it's listening on the 12345 port...");

                Socket client=serverSocket.accept();
                System.out.println("Client accepted!");

                serveClient(client, map);
                System.out.println("Client handled!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void serveClient(Socket client, HashMap<String, List<String>> map) {
        try(
            BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        ){
            for(String key: map.keySet()){
                writer.write(key);
                writer.newLine();
                writer.flush();
            }

            writer.write("END");
            writer.newLine();
            writer.flush();

            String source=reader.readLine();

            System.out.println("Client entered the name of the city!");
            List<String> flights;
            if((flights=map.get(source.toLowerCase()))!=null){
                for(String flight: flights){
                    writer.write(flight);
                    writer.newLine();
                    writer.flush();
                }

                writer.write("END");
                writer.newLine();
                writer.flush();
            } else {
                System.out.println("There are no flights for that city!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
