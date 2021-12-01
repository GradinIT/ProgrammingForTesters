package se.jocke.gerald;

import exercise.gerald.Fu;
import exercise.gerald.Ninja;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNinja {
    // VAD
    @Mock
    Fu fu;

    // VAR
    @InjectMocks
    Ninja ninja;

    @Test
    void testFuBar() {
        // HUR
        when(fu.bar()).thenReturn("fu-bar");
        Assertions.assertEquals("fu-bar", ninja.doNinja());
    }
}
