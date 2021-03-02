package se.jocke;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClassForMockitoSpy {
    private Integer integerValue;
    private String stringValue;

    public static String getClassName(){
        return ClassForMockitoSpy.getClassName();
    }
}