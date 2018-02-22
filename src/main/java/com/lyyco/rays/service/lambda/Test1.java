package com.lyyco.rays.service.lambda;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by lyy on 2018/1/15.
 */
public class Test1 {
    public static void main(String[]args){
        List<String> names = Lists.newArrayList();
        names.add("LYY");
        names.add("TTT");
//        List<String> lower1 = FluentIterable.from(names).transform(new Function<String, T>() {
//            @Nullable
//            @Override
//            public T apply(@Nullable String s) {
//                return s.toLowerCase();
//            }
//        });
        List<String> lower2 = names.stream().map((name)-> {return name.toLowerCase();}).collect(Collectors.toList());
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        List<Apple> apples = Lists.newArrayList();
        filterApples(apples,Apple::isGreenApple);
        filterApples(apples,(Apple a)-> a.getWeight() > 150);
    }


    public static List<Apple> filterApples(List<Apple> inventory,Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


    private static class Apple {
        public static boolean isGreenApple(Apple apple){
            return "green".equals(apple.getColor());
        }
        public String color;
        public int weight;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
