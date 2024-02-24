package Task_04;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class ZipMain {
    public static void main(String[] args) {
        try{
            long start=System.currentTimeMillis();

            BufferedInputStream bin=new BufferedInputStream((new FileInputStream("Week_01/Task_04/input_data.txt")));
            BufferedOutputStream bout=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("Week_01/Task_04/output_data.gz")));
            // You can also try the following command!
            // BufferedOutputStream bout=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("Week_01/Task_04/output_data.rar")));

            byte[] buffer=new byte[512];
            int read_bytes;
            while((read_bytes=bin.read(buffer))!=-1){
                bout.write(buffer, 0, read_bytes);
            }

            bin.close();
            bout.close();

            long end=System.currentTimeMillis();
            System.out.println("Program finished its execution after " + (end-start) + "ms!");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
