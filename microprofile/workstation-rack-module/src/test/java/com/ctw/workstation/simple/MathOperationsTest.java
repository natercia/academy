package com.ctw.workstation.simple;

import io.quarkus.logging.Log;
import org.junit.jupiter.api.*;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class MathOperationsTest {

    private static MathOperations mathOperations;

    @BeforeAll
    static void setup() {
        Log.info("Setting it up...");
        mathOperations = new MathOperations();
    }

    @BeforeEach
    void setupEach() {
        Log.info("Coisas e tal :3");
    }

    @Test
    void add() {
        //MathOperations mathOperations = new MathOperations();
        Assertions.assertAll(
                () -> assertEquals(5, mathOperations.add(2, 3)),
                () -> assertEquals(18, mathOperations.add(6, 12)),
                () -> assertEquals(1, mathOperations.add(1, 0)),
                () -> assertEquals(0, mathOperations.add(0, 0)),
                () -> assertEquals(-2, mathOperations.add(-4, 2)),
                () -> assertEquals(0, mathOperations.add(-2, 2))
        );
    }

    @Test
    @DisplayName(
        "Checks if dividing two elements throws the appropriate exception YAYYY"
    )
    void divide_by_zero() {

        //given
        //MathOperations mathOperations = new MathOperations();
        int dividend = 1;
        int divisor = 0;

        Supplier<String> supplier = () -> "gojo";
        //when
        //then
        Assertions.assertThrows(
                ArithmeticException.class, () -> mathOperations.divide(dividend, divisor)
        );

    }
}