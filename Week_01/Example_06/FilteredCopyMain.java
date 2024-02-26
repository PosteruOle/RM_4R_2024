package Example_06;

import java.io.*;
import java.util.Scanner;


public class FilteredCopyMain {
    public static void main(String[] args) {
        try(
            Scanner scanner=new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("Week_01/Example_06/input_data.txt"))));
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Week_01/Example_06/output_data.txt")));
        ){

            scanner.useDelimiter("\\b");

            while(scanner.hasNext()){
                String word=scanner.next();
                if(isValidName(word)){
                    writer.write(word);
                    writer.newLine();
                }
            }

            System.out.println("Done!");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidName(String word) {
        if(word.length()<2){
            return false;
        }

        if(!Character.isUpperCase(word.charAt(0))){
            return false;
        }

        return word.chars().skip(1).allMatch(Character::isLowerCase);
    }

}
