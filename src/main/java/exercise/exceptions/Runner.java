package exercise.exceptions;

public class Runner {
    private final String name = "ove";
    public static void main(String[] args) {
        Runner runners = new Runner();
        try {
            // inside the brackets we have code that potentially throws an exception
            runners.myMethod();
        }
        catch (Exception e) {
            if(e.getMessage().equals("Broken")) {
                // handle the broken error
                System.out.println("handle Broken");
            }
            else if (e.getMessage().equals("Werry Broken")) {
                // handle the werry broken error
                System.out.println("handle werry broken");
            }
        }
    }
    public int myMethod() {
        other();
        throw new MyException("Broken");
    }

    private void other() {
        throw new MyException("Werry Broken");
    }
}
