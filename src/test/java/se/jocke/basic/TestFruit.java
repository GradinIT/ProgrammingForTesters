package se.jocke.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFruit {
    @Test
    public void testCreateApple() {
        Fruit f = Apple.builder().name("Granny Smith").build();
        Assertions.assertAll("Apple",
                () -> assertNotNull(f),
                () -> assertTrue(f.isEatable()),
                () -> assertEquals("Granny Smith", f.getName())
        );
    }
}
