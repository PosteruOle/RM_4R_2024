package Primer_01;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

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
            FileInputStream fin=new FileInputStream("Nedelja_01/Primer_01/input.txt");
            FileOutputStream fout=new FileOutputStream("Nedelja_01/Primer_01/output.txt");
        ){
            //fin.read();
            //fout.write();

            BufferedInputStream bin=new BufferedInputStream(new FileInputStream("Nedelja_01/Primer_01/input.txt"));
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream("Nedelja_01/Primer_01/output.txt"));

            InputStreamReader reader=new InputStreamReader(new FileInputStream("Nedelja_01/Primer_01/input.txt"));
            OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("Nedelja_01/Primer_01/output.txt"));

            BufferedReader reader01=new BufferedReader(new InputStreamReader(new FileInputStream("Nedelja_01/Primer_01/input.txt")));
            InputStreamReader reader02=new InputStreamReader(new BufferedInputStream(new FileInputStream("Nedelja_01/Primer_01/input.txt")));

            BufferedWriter writer01=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Nedelja_01/Primer_01/output.txt")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
