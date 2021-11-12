package exercise.io;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class StringIO {
    public static void main(String[] args) throws IOException {
        Writer stringWriter = new StringWriter();
        for(int i = 10 ; i < 150 ; ++i) {
            stringWriter.write(i);
        }
        System.out.println(stringWriter.toString().trim());
    }
}
