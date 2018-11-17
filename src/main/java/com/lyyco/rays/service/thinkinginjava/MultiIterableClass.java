package com.lyyco.rays.service.thinkinginjava;

import java.util.*;

/**
 * 迭代器
 * Author liyangyang
 * 2018/11/17
 */
public class MultiIterableClass extends IterableClass {
    public Iterable<String> reversed() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int current = words.length - 1;

                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public String next() {
                        return words[current--];
                    }
                };
            }
        };
    }

    public Iterable<String> randomized() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                /*
                用ArrayList将Arrays.asList()的结果包装了起来：
                asList()的结果被传递到ArrayList构造器，这将创建一个引用words元素的ArrayList
                因此打乱引用的时候，不会修改底层words数组
                 */
                List<String> shuffled = new ArrayList<>(Arrays.asList(words));
                //shuffle没有影响到字符串数组words
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }
    public static void main(String[]args){
        MultiIterableClass mic = new MultiIterableClass();
        for(String s : mic.reversed()){
            System.out.print(s + " ");
        }
        System.out.println();
        for(String s : mic.randomized()){
            System.out.print(s + " ");
        }
        System.out.println();
        for(String s : mic){
            System.out.print(s + " ");
        }
    }
}

