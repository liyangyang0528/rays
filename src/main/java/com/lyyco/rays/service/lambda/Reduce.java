package com.lyyco.rays.service.lambda;

import org.assertj.core.util.Lists;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

/**
 * 规约操作
 * Author liyangyang
 * 2018/4/21
 */
public class Reduce {

    public static void main(String... args) {
        Dish d1 = new Dish("lyy", true, 100, Type.FISH);
        Dish d2 = new Dish("test", false, 200, Type.MEAT);
        Dish d3 = new Dish("crcr", true, 88800, Type.OTHER);

        List<Dish> menu = Lists.newArrayList(d1, d2, d3);
        int coto = menu.stream().collect(summingInt(Dish::getCalories));
        //将计数、平均值、最大、最小一次性返回
        IntSummaryStatistics summaryStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);

        int coto2 = menu.stream().collect(reducing(
                0,
                Dish::getCalories,
                Integer::sum));

        //分区
        menu.stream().collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 150)));
        //找到素食和非素食的食品中热量最高的菜
        menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories))
                                , Optional::get)));



    }
}
