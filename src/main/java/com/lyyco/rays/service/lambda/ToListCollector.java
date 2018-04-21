package com.lyyco.rays.service.lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

/**
 * 自定义的收集器  实现Collector接口
 * Author liyangyang
 * 2018/4/21
 */
public class ToListCollector<T> implements Collector<T,List<T>,List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;//创建集合操作的起始点
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;//累积遍历过的项目，原位修改累加器
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2) -> {//修改第一个累加器，将其与第二个累加器的内容合并
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();//恒等函数 t -> t
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH,CONCURRENT
        ));
    }
}
