package excersise.exceptions;

import java.io.File;
import java.io.IOException;

public class MyOwnRuntimeException extends RuntimeException{

    public MyOwnRuntimeException(String message){
        super (message);
    }


}