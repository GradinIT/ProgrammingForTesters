package excersise.two.lombok;

public class MySecondClassLombokRunner {
    public static void main(String[] args) {
        MySecondClassLombok mySecondClassLombok = MySecondClassLombok
                .builder()
                .firstName("Jocke")
                .lastName("Gradin")
                .age(Integer.valueOf(46))
                .build();

        System.out.println(mySecondClassLombok);

    }
}
