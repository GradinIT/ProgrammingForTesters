package excersise.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileExercise {
    //TODO: open an exsisting file and read text in it , print text in console
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/tmp/runar.txt")));
        String row;
        while(( row = bufferedReader.readLine()) != null) {
            System.out.println(row);
        }
    }
}
