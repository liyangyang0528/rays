package com.lyyco.rays.service.lambda;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import scala.App;

import javax.annotation.Nullable;
import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;
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
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        });
        apples.sort(Comparator.comparing(Apple::getWeight));
        Comparator<Apple> c = (Apple a1,Apple a2)-> a1.getWeight().compareTo(a2.getWeight());



        //TODO
        Function<Integer,Apple> c3 = Apple::new;
        Function<Integer,Apple> c4 = (weight)-> new Apple(weight);
        Apple a3 = c3.apply(110);
        Apple a4 = c4.apply(110);

        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> appless = map(weights,Apple::new);
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer e : list){
            result.add(f.apply(e));
        }
        return result;
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
        public Apple(Integer weight){
            this.weight = weight;
        }
        public static boolean isGreenApple(Apple apple){
            return "green".equals(apple.getColor());
        }
        public String color;
        public Integer weight;

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
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
