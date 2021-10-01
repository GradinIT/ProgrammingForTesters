package excersise.two.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MySecondClassLombok {
    // TODO: @see MySecondClassLombokRunner and explain why we can use this class the way we do
    private String aStringValue;
}
