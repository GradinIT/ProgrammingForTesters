package excersise.inheritence.realworld;

import java.io.Serializable;
import java.time.LocalTime;

public class Clock implements Runnable, Serializable  {
    public static void main(String[] args) {
        new Clock();
    }
    public Clock() {
        new Thread(this).start();
    }
    @Override
    public void run() {
        while(true) {
            System.out.println(LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
