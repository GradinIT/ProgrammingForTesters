package se.jensen.eazy;

import java.io.Serializable;

public class Divide implements Calculable {
    public Integer execute(Integer number,Integer number2){
        if( number == 0 || number2 == 0)
            throw new RuntimeException("cant be 0");
        return number/number2;
    }
}
