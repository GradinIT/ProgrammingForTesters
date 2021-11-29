package excersise.io;

import java.util.*;
        import java.nio.charset.StandardCharsets;
        import java.nio.file.*;
        import java.io.*;
public class arrayread
{
    public static List<String> readFileInList(String filename )
    {

        List<String> lines = Collections.emptyList();
        try
        {
            lines =
                    Files.readAllLines(Paths.get("test3.txt"), StandardCharsets.UTF_8);
        }

        catch (IOException e)
        {

            // do something
            e.printStackTrace();
        }
        return lines;
    }
    public static void main(String[] args)
    {
        List l = readFileInList("test3.txt");

        Iterator<String> itr = l.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());
    }
}