package excersise.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class OpenFileExercise {
    //TODO: open an exsisting file and read text in it , print text in console
    public static void main(String[] args) throws Exception {
        //File fileToRead = new File("/tmp/runar.txt");
       // Reader reader = new FileReader(fileToRead);
       /* int c;
        while ((c = reader.read()) != -1) {
            System.out.println("teckenvärde: " + (char) c + "= siffervärde: " + c);
        }
*/
        Writer stringWriter = new StringWriter();
        BufferedReader bufferedReader = new BufferedReader(new FileReader( new File("/tmp/runar.txt")));
        String row;
        while(( row = bufferedReader.readLine()) != null) {
            stringWriter.write(row);
            stringWriter.write("\n");
        }
        bufferedReader.close();
        String textContentFromFile = stringWriter.toString();
        System.out.println("Text from file ");
        System.out.println(textContentFromFile);
        textContentFromFile += "\njocke lägger ut texten";
        System.out.println("text from string buffer same content as file but from a string ");
        bufferedReader = new BufferedReader(new StringReader(textContentFromFile));
        while(( row = bufferedReader.readLine()) != null) {
            System.out.println(row);
        }
    }
}
