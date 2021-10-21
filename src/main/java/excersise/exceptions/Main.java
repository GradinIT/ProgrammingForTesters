package excersise.exceptions;

public class Main {
    public static void main(String[] args)  {
        try {
            methodThatThrowsUncheckedException();
            methodThatThrowsCheckedException();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("resten av koden");
    }

    private static void methodThatThrowsUncheckedException() {
        throw new MyException("Carola kastar grejer på Runar");
    }

    private static void methodThatThrowsCheckedException() throws MyOtherException {
        throw new MyOtherException("Carola kastar grejer på Runar");
    }

    static class MyException extends RuntimeException {
        public MyException(String message) {
            super(message);
        }
    }

    static class MyOtherException extends Exception {
        public MyOtherException(String message) {
            super(message);
        }
    }
}
