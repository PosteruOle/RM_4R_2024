package Primer_02;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyImageMain {
    public static void main(String[] args) {
        try{
            long start=System.currentTimeMillis();

            FileInputStream fin=new FileInputStream("Nedelja_01/Primer_02/input_image.png");
            FileOutputStream fout=new FileOutputStream("Nedelja_01/Primer_02/output_image.png");

            int read_bytes;
            while((read_bytes= fin.read())!=-1){
                fout.write(read_bytes);
            }

            fin.close();
            fout.close();

            long end=System.currentTimeMillis();
            System.out.println("Program finished its execution in " + (end-start) + "ms!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
