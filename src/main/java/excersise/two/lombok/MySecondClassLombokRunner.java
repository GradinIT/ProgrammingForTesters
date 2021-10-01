package excersise.two.lombok;

public class MySecondClassLombokRunner {
    public static void main(String[] args) {
        MySecondClassLombok mySecondClassLombok = MySecondClassLombok.builder().aStringValue("value").build();
        System.out.println(mySecondClassLombok.getAStringValue());
        mySecondClassLombok.setAStringValue("new value");
        System.out.println(mySecondClassLombok.getAStringValue());
    }
}
