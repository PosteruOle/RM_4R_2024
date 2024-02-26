package Example_01;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class IntroMain {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to Computer Networks course! :)");

        // Collection streams
        List<String> array=new LinkedList<>();
        array.stream();

        //IO Streams
        // The first class hierarchy!
        InputStream in;
        OutputStream out;

        // The second class hierarchy!
        Reader r;
        Writer w;

        try(
            FileInputStream fin=new FileInputStream("Week_01/Example_01/input.txt");
            FileOutputStream fout=new FileOutputStream("Week_01/Example_01/output.txt");
        ){
            //fin.read();
            //fout.write();

            BufferedInputStream bin=new BufferedInputStream(new FileInputStream("Week_01/Example_01/input.txt"));
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream("Week_01/Example_01/output.txt"));

            InputStreamReader reader=new InputStreamReader(new FileInputStream("Week_01/Example_01/input.txt"));
            OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("Week_01/Example_01/output.txt"));

            BufferedReader reader01=new BufferedReader(new InputStreamReader(new FileInputStream("Week_01/Example_01/input.txt")));
            InputStreamReader reader02=new InputStreamReader(new BufferedInputStream(new FileInputStream("Week_01/Example_01/input.txt")));

            BufferedWriter writer01=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Week_01/Example_01/output.txt")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
