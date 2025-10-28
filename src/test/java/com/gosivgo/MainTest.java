package com.gosivgo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void mainMethodShouldExecute() {
        // Проверяем что main метод не бросает исключений
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    void simpleAssertionWorks() {
        int result = 2 + 2;
        assertEquals(4, result);
    }
}