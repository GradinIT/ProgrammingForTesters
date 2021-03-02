package se.jocke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.MockedSatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestClassForMockitoSpy {
    @Spy private ClassForMockitoSpy classForMockitoSpy = ClassForMockitoSpy.builder()
            .integerValue(12).stringValue("Hello").build();

    @Test
    public void testClassForMockitoSpy(){
        doReturn(120).when(classForMockitoSpy).getIntegerValue();
        Assertions.assertAll(
                () -> assertEquals(120,classForMockitoSpy.getIntegerValue()),
                () -> assertEquals("Hello",classForMockitoSpy.getStringValue())
        );
        verify(classForMockitoSpy,times(1)).getIntegerValue();
        verify(classForMockitoSpy).getStringValue();
    }
/*
    @Test
    public void testStaticMethodMock(){
        try (MockedStatic<ClassForMockitoSpy> mockedStatic = Mockito.mockStatic(ClassForMockitoSpy.class)){
            mockedStatic.when(() -> ClassForMockitoSpy.getClassName()).thenRetrun(getClass().getName());
            assertEquals("test.java.se.jocke.TestClassForMockitoSpy",ClassForMockitoSpy.getClassName());
        }
    }

    @Test
    public void testMockingAddStatic(){
        try (MockedStatic<Add> mockedStatic = Mockito.mockStatic(Add.class)){
            mockedAddStatic.when(() -> Add.calculate(anyInt(),anyInt())).thenRetrun(10);
            Assertions.assertEquals(11,Add.calcualate(10,1));
        }
    }

 */
}