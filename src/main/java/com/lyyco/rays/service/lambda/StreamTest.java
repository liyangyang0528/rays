package com.lyyco.rays.service.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lyy on 2018/2/23.
 */
public class StreamTest {
    public static void main(String[]args){
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream().filter(i -> i%2 == 0)
                .distinct()
                .forEach(System.out::println);

        List<String> words = Arrays.asList("java8","Lambdas","In Action");
        List<Integer> wordLength = words.stream().map(String::length).collect(Collectors.toList());
        //flatMap
        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        //实现对两个list的排列组合
        List<Integer> num1 = Arrays.asList(1,2,3);
        List<Integer> num2 = Arrays.asList(3,4);
        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream()
                        //.filter(j -> (i+j)%3 == 0)
                        .map(j -> new int[]{i,j})
        ).collect(Collectors.toList());


        Stream<String> stream = Stream.of("java8","Lambda");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }
}
