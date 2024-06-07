package June_0_2024.Zadatak_1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class ThreadRunnable implements Runnable {
    String line;
    public ThreadRunnable(String line) {
        this.line=line;
    }

    @Override
    public void run() {
        try{
            URL url = new URL(this.line);

            if(url.getProtocol().equalsIgnoreCase("file")){
                try{
                    BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(url.getPath())));

                    int counter=0;
                    while(reader.readLine()!=null){
                        counter++;
                    }

                    synchronized (System.out){
                        System.out.println("OK " + url.getPath() + " " + counter);
                    }

                } catch (FileNotFoundException e) {
                    synchronized (System.out){
                        System.out.println("NOT FOUND " + url.getPath());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                synchronized (System.out){
                    System.out.println("FORWARD " + this.line + " [" + new Date().toString() + "]");
                }
            }
        } catch (MalformedURLException e) {
            synchronized (System.out){
                System.out.println("BAD URL \"" + this.line + "\"");
            }
        }
    }
}
