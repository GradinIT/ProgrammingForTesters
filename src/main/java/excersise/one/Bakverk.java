package excersise.one;

import lombok.Setter;

@Setter



public class Bakverk { //en klass. en klass kan ha egenskaper och beeteenden, kan hålla ett state.
    // den håller ett state genom att ha instansvariabler / fält
    private Integer size; // en instansvariabel som är deklarerad
    private String name;

    public static void main(String[] args) {

        int i = 0; //primär datatyp för heltal- primär datatyper kan inte innehålla null
        float f = (float) 0.0;
        long l =  0; // stort heltal. l är en variabel av dataypen long

        char c = 'h'; //tecken
        f = i;
        System.out.println(f);
        i = (int)f;
        System.out.println(i);


        Integer integer = 0; //wrapperclass för heltal - motsvarar primärtypen int
        //wrapper kan innehålla null
        if (integer != null){
            System.out.println(++integer);
        }

        System.out.println(++integer);


        Long longValue = 123567789L; //wrapperklass som motsvarar primärtypen long
        //longValie är en variabel av dataypen Long

        Character character = 'r';


        Bakverk bakverk1 = new Bakverk();
        // objekt. metod (value)
        bakverk1.setSize(4);
        bakverk1.setName("bakverk1");

        Bakverk bakverk2 = new Bakverk();
        bakverk2.setSize(77);
        bakverk2.setName("bakeverk2");
        System.out.println(bakverk1);
        System.out.println(bakverk2);
    }
}
