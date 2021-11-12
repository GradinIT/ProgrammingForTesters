package exercise.two.constructor;

public class MySecondClass {
    //TODO: add a member of type String and create a metod to set the value and a method to get the value
    // also make the class runnable

    private String runar;

    public MySecondClass(String runar) {
        this.runar = runar;
    }
    public void setRunar(String runar) {
        this.runar = runar;
    }
    public String getRunar() {
        return this.runar;
    }

    public static void main(String[] args) {
        MySecondClass runar = new MySecondClass("Runar är inte kompis med Carola längre, jaktolycka");
        System.out.println(runar.getRunar());
        runar.setRunar("Det tycker alla bara är trevligt för Runar var från Västra Medelpad.");
        System.out.println(runar.getRunar());
    }
}
