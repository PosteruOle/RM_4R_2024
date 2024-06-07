package June_0_2024.Zadatak_1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("PREVIOUS_PRACTICAL_EXAMS/June_0_2024/Zadatak_1/log.txt")));
            String line;
            while ((line = reader.readLine()) != null){
                new Thread(new ThreadRunnable(line)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
