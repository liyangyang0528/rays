package com.lyyco.rays.service.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.lyyco.rays.service.lambda
 *
 * @Author liyangyang
 * 2018/3/26
 */
public class StreamSortedTest {
    public static void main(String... args) {


        List<Integer> list = new ArrayList<>(Arrays.asList(9, 5, 6, 3, 2, 1, 10, 2, 3, 4));
        List results = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Integer integer : list) {
            System.out.println(integer);
        }
        results.forEach(System.out::println);
    }
}
