package excersise.two.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class MySecondClassLombok {
    private final String firstName;
    private final String lastName;
    private final Integer age;
}
