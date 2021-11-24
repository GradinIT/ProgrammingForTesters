package excersise.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewFileExercise {
    public static void main(String[] args) throws IOException {

        File file = new File("exercise.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Detta är en ny övning");
        writer.close();

        }
    }
