package Primer_02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyImageBufferedMain {
    public static void main(String[] args) {
        try{
            long start=System.currentTimeMillis();

            BufferedInputStream input=new BufferedInputStream(new FileInputStream("Nedelja_01/Primer_02/input_image.png"));
            BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream("Nedelja_01/Primer_02/faster_output_image.png"));

            byte[] buffer=new byte[512];
            int read_bytes;
            while((read_bytes=input.read(buffer))!=-1){
                output.write(buffer, 0, read_bytes);
            }

            input.close();
            output.close();

            long end=System.currentTimeMillis();
            System.out.println("Program finished its execution in " + (end-start) + "ms!");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
