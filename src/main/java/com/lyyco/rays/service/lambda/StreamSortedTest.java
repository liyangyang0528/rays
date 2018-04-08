package com.lyyco.rays.service.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * com.lyyco.rays.service.lambda
 * 并行流要注意的几点
 *
 * @Author liyangyang
 * 2018/3/26
 */
public class StreamSortedTest {

    public static void main(String... args) {
        /**
         * reduce操作会改变list的第一个元素？？ ---found by PuShuai
         * 没能再现这种情况
         */
        List<Integer> list = new ArrayList<>(Arrays.asList(9, 5, 6, 3, 2, 1, 10, 2, 3, 4));
        List results = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Integer integer : list) {
            System.out.println(integer);
        }
//        results.forEach(System.out::println);

        /**2.Interference
         *
         * Lambda expressions in stream operations should not interfere.
         * Interference occurs when the source of a stream is modified
         * while a pipeline processes the stream.
         * For example, the following code attempts to
         * concatenate the strings contained in the List listOfStrings.
         * However, it throws a ConcurrentModificationException
         */

        List<String> listOfStrings =
                new ArrayList<>(Arrays.asList("one", "two"));

        // This will fail as the peek operation will attempt to add the
        // string "three" to the source after the terminal operation has
        // commenced.
        try {
            String concatenatedString = listOfStrings
                    .stream()

                    // Don't do this! Interference occurs here.
//                    .peek(s -> listOfStrings.add("three"))

                    .reduce((a, b) -> a + " " + b)
                    .get();

            System.out.println("Concatenated string: " + concatenatedString);

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.toString());
        }
        /**
         * 3.Stateful Lambda Expressions
         *
         * Avoid using stateful lambda expressions as parameters
         * in stream operations. A stateful lambda expression is
         * one whose result depends on any state that might change
         * during the execution of a pipeline. The following example
         * adds elements from the List listOfIntegers to a new List
         * instance with the map intermediate operation.
         * It does this twice, first with a serial stream and then
         * with a parallel stream
         */
        List<Integer> serialStorage = new ArrayList<>();
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(5,0,9,2,1,2));
        System.out.println("Serial stream:");
        listOfIntegers
                .stream()
                // Don't do this! It uses a stateful lambda expression.
                .map(e -> {
                    serialStorage.add(e);
                    return e;
                })

                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        serialStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Parallel stream:");
        List<Integer> parallelStorage = Collections.synchronizedList(
                new ArrayList<>());
        listOfIntegers
                .parallelStream()

                // Don't do this! It uses a stateful lambda expression.
                .map(e -> {
                    parallelStorage.add(e);
                    return e;
                })

                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");
    }
}
