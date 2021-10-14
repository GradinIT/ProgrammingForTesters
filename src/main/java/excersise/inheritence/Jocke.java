package excersise.inheritence;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Setter
@Getter
public class Jocke {
    private final String firstName;
    private final String lastName;
    private Integer age;

    public static void main(String[] args) {
        Jocke j1 = Jocke.builder().firstName("Joakim").age(4).lastName("Gradin").build();
        Jocke j2 = Jocke.builder().firstName("Joakim").lastName("Gradin").build();
        System.out.println(j1);
        System.out.println(j2);
    }
}
