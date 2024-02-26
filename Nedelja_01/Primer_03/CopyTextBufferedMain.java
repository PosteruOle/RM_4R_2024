package Primer_03;

import java.io.*;

public class CopyTextBufferedMain {
    public static void main(String[] args) {
        try{
            long start=System.currentTimeMillis();

            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("Nedelja_01/Primer_03/input_data.txt")));
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Nedelja_01/Primer_03/faster_output_data.txt")));

            char[] buffer=new char[512];
            int read_bytes;
            while((read_bytes=reader.read(buffer))!=-1){
                writer.write(buffer, 0, read_bytes);
            }

            reader.close();
            writer.close();

            long end=System.currentTimeMillis();
            System.out.println("Program finished its execution after " + (end-start) + "ms!");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
