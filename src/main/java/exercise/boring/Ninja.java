package exercise.boring;

import org.springframework.beans.factory.annotation.Autowired;

public class Ninja {
    @Autowired
    private Fu fu;

    public String doNinja() {
        return fu.bar();
    }
}
