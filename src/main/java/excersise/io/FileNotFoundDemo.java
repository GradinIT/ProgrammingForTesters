package excersise.io;
import java.io.*;


public class FileNotFoundDemo {

    static void openFile(String fileName) throws FileNotFoundException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            //...
        } catch (FileNotFoundException e) {
            System.out.println("Error while opening file: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        openFile("non-existent_file.txt");
    }
}
