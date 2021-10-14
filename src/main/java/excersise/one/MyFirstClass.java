package excersise.one;

public class MyFirstClass {
    public static void main(String[] args) {
        for(int i = 0 ; i < args.length ; i++) {
            System.out.println("index:" + i + " value in array:" +args[i]);
        }
        System.out.println("index:" + 4 + " value in array:" +args[4]);
    }
}
