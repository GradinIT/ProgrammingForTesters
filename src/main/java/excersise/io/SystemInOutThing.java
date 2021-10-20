package excersise.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SystemInOutThing {
    public static void main(String[] args) throws IOException {
        System.out.println("Who r you: ");
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(scanner.readLine());
    }
}
