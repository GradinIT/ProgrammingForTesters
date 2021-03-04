package se.jocke.exercises.basic;

import org.springframework.beans.factory.annotation.Autowired;

public class Ninja {
    // skapar en instans av min Fu interface
    @Autowired
    private Fu fu;

    public String doNinja(){
        return fu.bar();
    }
}
