package com.ctw.workstation.simple;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Exercises {

    public static List<String> one(){
        String[] words = new String[7];
        words[0] = "Java";
        words[1] = "FS";
        words[2] = "Academy";
        words[3] = "CTW";
        words[4] = "BMW";
        words[5] = "/";
        words[6] = "-";

        List<String> newString = Arrays.stream(words)
                .filter(x -> {
                    return x.matches("[A-Za-z]*");
                })
                .map(String::toUpperCase).toList();

        return newString;
    }

    public static Map<String, Long> two(){
        List<String> numbers = Arrays.asList("43", "21", "54", "89","137", "142", "751", "89", "137");
        Map<String, Long> set = numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet())
                .stream().collect(
                        Collectors.groupingBy(x -> x%2 == 0 ? "EVEN" : "ODD", Collectors.summingLong(Integer::intValue))
                                                                            // Collectors.counting()
                );

        return set;

    }

    public static void three(){
        String word = "Gojo";
        Optional<String> op_string = Optional.of(word);
        String none = null;
        Optional<String> none_string = Optional.ofNullable(none);
        op_string.ifPresent(System.out::println);
        none_string.ifPresentOrElse(System.out::println,() -> {System.out.println("No value..:(");});
        Optional<Integer> len = Optional.of(word.length());
        len.ifPresent(System.out::println);
    }

    public static void four(){
        List<String> newList = Arrays.asList( "Java", "FS", "Academy", "CTW", "BMW", "/", "-" );
        Predicate<String> alphabetical = word -> word.matches("[A-Za-z]*");
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        Function<String, Integer> getLength = String::length;
        Function<Integer, String> inBrackets = word -> {return "(" + word + ")";};

        var composedFunction = toUpperCase.andThen(getLength.andThen(inBrackets));

        var outcome = newList.stream().filter(alphabetical).map(composedFunction).toList();

        System.out.println(outcome);
    }

    public static void five(){
        List<String> numbers = Arrays.asList("43", "21", "54", "89","137", "142", "751", "89", "137");

        Function<String, Integer> toInteger = Integer::parseInt;
        UnaryOperator<Integer> square = a -> {
            return a * a;
        };

        var highOrder = toInteger.andThen(square);

        var outcome = numbers.stream()
                .map(highOrder)
                .collect(Collectors.toList());

        System.out.println(outcome);
    }


}
