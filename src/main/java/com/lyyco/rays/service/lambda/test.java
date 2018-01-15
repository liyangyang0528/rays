package com.lyyco.rays.service.lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import javax.swing.Timer;
/**
 * Created by lyyco on 2018/1/10.
 */
public class test {
    public static void main(String[]args){
        String[] plantes = new String[]{
          "Mercury","Venus","Earth"
        };
        Arrays.sort(plantes,(first,second)-> first.length()-second.length());
        Timer t = new Timer(1000,event->System.out.println("The time is "+new Date()));
            t.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }).start();
        new Thread(()-> System.out.println("hello lambda")).start();
        //Collections.sort();
        String[] strs = {"Lambdas","default","Date and Time"};
        List<String> features = new ArrayList<>(Arrays.asList(strs));
        features.forEach(System.out::println);
        List<Integer> costBeforeTax = new ArrayList<>(Arrays.asList(100,200,300,400,500));
        costBeforeTax.stream().map((cost)-> cost + 12*cost).forEach(System.out::println);

        //得到一个线程安全的Set
        Set<String> words = ConcurrentHashMap.newKeySet();

        //任何集合类都可以使用同步包装器变成线程安全的
        List<String> syncArraylist = Collections.synchronizedList(new ArrayList<String>());
        Map<Integer,String> syncMap =Collections.synchronizedMap(new HashMap<Integer,String>(

        ));
        /**
         * java语法糖
         */
        //自动拆箱
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e ==f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(a + b));
        //泛型擦除


        //java语言的条件编译
        if(true){
            System.out.println("block1");
        }else{
            System.out.println("block2");
        }

        //使用java.util.function
        List input = new ArrayList(Arrays.asList(new String[]{"apple","orange","pear"}));
//        List lengths = test.map(input,(String astr) -> astr.length());
//        List uppercase = test.map(input,(String v) -> v.toUpperCase());

        //stream().map
        List<Integer> costBeforeTaxs = Lists.newArrayList(Arrays.asList(100,200,300,400,500));
        //costBeforeTaxs.stream().map((cost) -> cost+2*cost).forEach(System.out::println);
        costBeforeTaxs.stream().map((cost) -> cost=1).forEach(System.out::println);
        double bill = costBeforeTaxs.stream().map((cost)-> cost).reduce((sum,cost)->sum+cost).get();
        System.out.print(bill);
    }
    //泛型擦除
//    public static void method(List<String> list){
//        System.out.print("String");
//    }
//    public static void method(List<Integer> list){
//        System.out.print("Integer");
//    }

//    public static  String method(List<String> list){
//        return "";
//    }
    public static int method(List<Integer> list){
        return 0;
    }


    public static List map(List<String> input, Function processor){
        ArrayList result = Lists.newArrayList();
        input.forEach((String t)->{
            result.add(processor.apply(t));
        });

        return  result;
    }
}

