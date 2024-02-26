package Primer_05;

import java.io.*;
import java.util.Scanner;

public class PrintStreamMain {
    public static void main(String[] args) {
        try (PrintStream printer = new PrintStream(new BufferedOutputStream(new FileOutputStream("Week_01/Task_05/printer_output.txt")))) {
            printer.print("Hello");
            printer.println(" world!");
            double pi=3.1415;
            printer.printf("Number PI is equal to %f...\n", pi);
            printer.println("There are many other things we would like to leave here!");
            printer.println("But we don't have enough time!");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        try(Scanner sc=new Scanner(new FileInputStream("Week_01/Task_05/printer_output.txt"))){
           while(sc.hasNextLine()){
               System.out.println(sc.nextLine());
           }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
