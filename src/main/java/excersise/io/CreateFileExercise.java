package excersise.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileExercise {

    //TODO: create a file and write som text to it
    // check in cmd that file is created ( or explorer )
    public static void main(String[] args) throws IOException {
        File file = new File("/tmp/runar.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Carola gillar inte dig lämngre\n");
        writer.close();
    }
}
