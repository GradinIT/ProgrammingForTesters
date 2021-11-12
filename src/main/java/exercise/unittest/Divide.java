package exercise.unittest;
// Övning 2
public class Divide {
    public static double calculate( double a , double b) {
        if(b <= 0)
            throw new ArithmeticException("division by zero");
        return a / b ;
    }
}
