package com.lyyco.rays.service.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * lambda学习测试用
 * com.lyyco.rays.service.lambda
 *
 * @Author liyangyang
 * 2018/3/12
 */
public class LambdaTest {
    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(br);
        }
    }
    public static void main(String...args) throws IOException {

//        String twoLines = processFile((BufferedReader br) -> br.readLine()+br.readLine());

        /*
        flapMap
         */
        List<String> words = new ArrayList<>(Arrays.asList("Java8","Lambda","In","Action"));
        List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
        List<String[]> stringss = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        /*
        Array's stream
         */
        String[] arrayOfWords = {"Goodbye","World"};
        //接受一个数组并产生一个流
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        List<Stream<String>> stringsss =  words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        //练习题：返回数组中每个数的平方
        int[] ints ={1,2,3,4};
        int[] intss = Arrays.stream(ints).map(x -> x*x).toArray();
        //练习题：给定两个数字列表，返回所有的数对
        List<Integer> number1 = Arrays.asList(1,2,3);
        List<Integer> number2 = Arrays.asList(3,4);
        List<int[]> pairs =
                number1.stream()
                .flatMap(i -> number2.stream()
                        .map(j -> new int[] {i,j}))
                        .collect(toList());





    }

}
