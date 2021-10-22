package excersise.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileExercise {
    public static void main(String[] args) throws IOException {
        File file = new File("/tmp/runar.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Carola gillar inte dig\nHasse\ngillar inte dig heller\n");
        writer.write("Carola gillar inte dig igen \n");
        writer.write("Carola gillar inte dig faktiskt fortfarande \n");
        writer.write("Carola gillar inte dig bara så du vet\n");
        writer.close();
    }
}
