package exercise.one;

public class MyFirstClass {
    // instance property
   private String myString;
    // Constructor
   public MyFirstClass(String value) {
       this.myString = value;
   }
    public MyFirstClass(String hej,String hopp) {
        this.myString = hej + " " + hopp;
    }
   public MyFirstClass(Integer i) {
       this.myString = i.toString();
   }
    // instance method
   public String getMyString() {
       return this.myString;
   }
}
