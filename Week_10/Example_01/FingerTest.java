package Example_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FingerTest {
    // Run in terminal: finger | nc -l 12345
    // Then start the program!
    public static void main(String[] args) throws MalformedURLException {
        // URL url=new URL(null, "finger://localhost:12345/usernames", new Handler());
        URL url = new URL(null, "finger://localhost:12345/admin", new Handler());

        try{
            URLConnection connection=url.openConnection();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while((line=reader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
