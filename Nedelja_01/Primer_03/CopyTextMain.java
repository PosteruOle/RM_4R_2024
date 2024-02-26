package Primer_03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CopyTextMain {
    public static void main(String[] args) {
        try{
            long start=System.currentTimeMillis();

            InputStreamReader reader=new InputStreamReader(new FileInputStream("Nedelja_01/Primer_03/input_data.txt"));
            OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("Nedelja_01/Primer_03/output_data.txt"));

            int read_bytes;
            while((read_bytes= reader.read())!=-1){
                writer.write(read_bytes);
            }

            reader.close();
            writer.close();

            long end=System.currentTimeMillis();
            System.out.println("Program finished its execution after " + (end-start) + "ms!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
