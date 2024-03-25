package Example_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

// https://agify.io/
// URL example: https://api.agify.io?name=Petar&age=24&country_id=SRB
public class Main {
    public static final String base_url = "https://api.agify.io";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name:");
        String name = scanner.nextLine();

        System.out.println("Please enter your age:");
        String age= scanner.nextLine();

        System.out.println("Please enter your countries id (rs or /):");
        String country_id = scanner.nextLine();

        Query q = new Query(base_url);
        q.add("name", name);
        q.add("age", age);

        if (!country_id.equalsIgnoreCase("/")) {
            q.add("country_id", country_id);
        }

        try {
            URL url = q.getURL();
            System.out.println(url);
            URLConnection connection = url.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
