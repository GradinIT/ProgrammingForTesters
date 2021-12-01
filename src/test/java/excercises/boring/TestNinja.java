package excercises.boring;

import exercise.boring.Fu;
import exercise.boring.Ninja;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNinja {
    @Mock
    Fu fu;

    @InjectMocks
    Ninja ninja;

    @Test
    public void testFuBar() {
        when(fu.bar()).thenReturn("fu-bar");
        Assertions.assertEquals("fu-bar",ninja.doNinja());
    }
}
