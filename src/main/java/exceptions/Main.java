package exceptions;

public class Main {
    public static void main(String[] args) {
        try{
            methodThatThrowsException();
        } catch (MyOtherExcption myException){
            System.out.println(myException.getCause());
            System.out.println(myException.getMessage());
            StackTraceElement[] stackTraceElements = myException.getStackTrace();
            myException.printStackTrace();
        }
        finally {
            System.out.println("kod som alltid körs");
        }

    }

private static void methodThatThrowsException(){
    throw new MyOtherExcption("expection1"); //egendefinerat, fördefinerat exception

}
static class MyOtherExcption extends RuntimeException{   //fördefinerad unchecked exception
    public MyOtherExcption(String message) {
        super(message);
    }
}
}