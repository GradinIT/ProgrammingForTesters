package excersise.exceptions;

public class Runner {
    private final String name = "ove";
    public static void main(String[] args) throws IllegalAccessException{
        Runner[] runners = {new Runner(),new Runner()};
        try {
            runners[0].myMethod();
        }
        catch (MyException myException) {
            System.out.println(myException.getMessage());
            throw new IllegalAccessException("Jätte-"+myException.getMessage());
        }
    }
    public int myMethod() {
        throw new MyException("Broken");
    }
}
