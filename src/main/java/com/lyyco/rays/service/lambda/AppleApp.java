package com.lyyco.rays.service.lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
/**
 * constructor reference
 * Created by lyy on 2018/2/23.
 */
public class AppleApp {

    static Map<String,Function<Integer,Fruit>> map = new HashMap<>(12);
    static {
        map.put("apple",Apple::new);
        map.put("orange",Orange::new);
    }
    public static Fruit giveMeFruit(String fruit,Integer weight){
        return  map.get(fruit.toLowerCase()).apply(weight);
    }

    public static List<Apple> map(List<Integer> weights, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        weights.forEach((we)-> result.add(function.apply(we)));
        return result;
    }


    public static void main(String[]args){

        BiFunction<String,Integer,Apple> c1 = Apple::new;
        //等同于下面的;调用apply方法会将color,weight应用到右边的lambda，并返回Apple
        BiFunction<String,Integer,Apple> c2 = (color,weight)-> new Apple(color,weight);
        Apple a1 = c1.apply("green",110);

        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> apples = map(weights,Apple::new);
        System.out.println(apples.size());

        List<Apple> apps = Lists.newArrayList();
        Comparator<Apple> c = Comparator.comparing((a)-> a.getWeight());
        apps.sort(Comparator.comparing((a)-> a.getWeight()));
        apps.sort(Comparator.comparing(Apple::getWeight));
        //Composing Comparators
        apps.sort(comparing(Apple::getWeight).reversed());
        //Chaining Comparators
        apps.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        //Composing Predicates
        Predicate<Apple> redApple = (app)-> "red".equals(app.getColor());
        Predicate<Apple> notRedApple = redApple.negate();

        //Composing Functions


    }
}
