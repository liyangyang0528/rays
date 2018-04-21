package com.lyyco.rays.service.lambda;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

import java.util.stream.Stream;

/**
 * Author liyangyang
 * 2018/4/21
 */
public class MapAndFlapMap {
    public static void main(String... args) {
        List<String> words = Arrays.asList("java8", "lambda", "in", "action");
        /**1.
         * 任务是
         * 返回一个列表，列出各不相同的字符
         */
        //错误写法
        List<Stream<String>> strs = words.stream()//Stream<String>
                .map(word -> word.split(""))//Stream<String[]>
                .map(Arrays::stream)//Stream<Stream<String>>
                .distinct()
                .collect(toList());//这里的tolist是将外层的Stream终端操作为List

        String[] arrayOfWords = {"j", "a", "v", "a"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        //FLATMAP
        List<String> uniqueCharacters =
                words.stream()//Stream<String>
                        .map(w -> w.split(""))//String<String[]>
                        //各个数组不是分别映射成一个流，而是映射成流的内容
                        .flatMap(Arrays::stream)//Stream<String>
                        .distinct()
                        .collect(toList());

        /*******************************************************/


        /**
         * 2.
         * 任务是给定两个数字列表，返回所有的数对
         * 【1，2，3】 【3，4】
         * 返回【（1，3），（1，4），（2，3），（2，4），（3，3），（3，4）】
         * ps:简单起见用两个元素的数组代表数对
         */
        Integer[] inte1 = {1, 2, 3};
        Integer[] inte2 = {3, 4};

        List<Stream<Integer[]>> resu =  Arrays.stream(inte1)        //Stream<Integer>
                .map((it1) -> Arrays
                        .stream(inte2)      //Stream<Integer>
                        .map((it2) -> new Integer[]{it1, it2}))//里层是Stream<Integer[]>
                        //再加上外层的map就是Stream<Stream<Integer[]>>
                        .collect(toList());

        List<Integer[]> flatresu =  Arrays.stream(inte1)        //Stream<Integer>
                .flatMap((it1) -> Arrays
                        .stream(inte2)      //Stream<Integer>
                        .map((it2) -> new Integer[]{it1, it2}))//里层是Stream<Integer[]>
                //再加上外层的flatmap就是Stream<Integer[]>
                .collect(toList());


    }


}
