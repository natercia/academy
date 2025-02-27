package com.ctw.workstation.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HelloAcademyTest {

    @ParameterizedTest
    @MethodSource(value = {"hello_academy_with_valid_name"})
    void hello_academy_with_valid_name(String name) {
        //given
        //String name = "Gojo";
        HelloAcademy hA = new HelloAcademy();

        //when
        String result = hA.sayHello(name);
        //then
        //Assertions.assertEquals("Hello " + name, result,  "Matching result for sayHello is equal to 'Hello " + name + "'");
    }

    public static Stream<Arguments> hello_academy_with_valid_name() {
        return Stream.of(
                Arguments.of("Hello Gojo", "Gojo"),
                Arguments.of("Hello ", ""),
                Arguments.of(null, "Hello")
        );
    }

    @Test
    void hello_academy_with_null_name() {
        //given
        String name = null;
        HelloAcademy hA = new HelloAcademy();

        //when
        String result = hA.sayHello(name);
        //then
        Assertions.assertEquals("Hello", result,  "Matching result for sayHello is equal to 'Hello'");
    }

    @Test
    void hello_academy_with_empty_name() {
        //given
        String name = "";
        HelloAcademy hA = new HelloAcademy();

        //when
        String result = hA.sayHello(name);
        //then
        Assertions.assertEquals("Hello ", result,  "Matching result for sayHello is equal to 'Hello '");
    }
}