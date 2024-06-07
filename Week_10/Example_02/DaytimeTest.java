package Example_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DaytimeTest {
    public static void main(String[] args) throws IOException {
        //URL url = new URL("daytime://localhost:8765");
        URL url = new URL(null, "daytime://localhost:8765", new Handler());

        // URLConnection conn = url.openConnection();
        var connection = url.openConnection();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null)
                System.out.println(line);
        }
    }
}
