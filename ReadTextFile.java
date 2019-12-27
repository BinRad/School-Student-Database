package Project2;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadTextFile {
    private static Scanner input;
    public static void Main(String[] args){
        openFile();
        readFile();
        closeFile();

    }
    public static void openFile(){
        try{
            File file = new File("C:\\Users\\BRade\\Downloads\\emma.txt");
            input = new Scanner(file);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in open file");
        }
    }
    public static String readFile(){
        String outString = null;
        try{
            while(input.hasNext()){
                String s = input.next();
                outString += s;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in read file");
        }
        System.out.println(outString);
        return outString;
    }
    public static void closeFile(){
        if(input == null){
            input.close();
        }
        System.out.println("Error in closing file");
    }
}
