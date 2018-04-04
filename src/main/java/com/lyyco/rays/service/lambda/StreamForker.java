package com.lyyco.rays.service.lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 定义一个StreamForker，在一个流上执行多个操作
 * Author liyangyang
 * 2018/4/4
 */
public class StreamForker<T> {

    private final Stream<T> stream;
    private final Map<Object, Function<Stream<T>, ?>> forks =
            new HashMap<>();

    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    /**
     * @param key key参数，通过它你可以取得操作的结果，并将这些键/函数对累积到一个内部的Map中
     * @param f   Function参数，它对流进行处理，将流转变为代表这些操作结果的任何类型
     * @return
     */
    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
        //使用一个键对流上的函数进行索引
        forks.put(key, f);
        //返回this从而多次流畅地调用fork方法
        return this;
    }

    //所有由fork方法添加的操作的执行都是通过getResults方法的调用触发的
    //该方法返回一个Results接口的实现
    //这一接口只有一个方法，你可以将fork方法中使用的key对象作为参数传入
    // 方法会返回该键对应的操作结果
    public Results getResults() {
        ForkingStreamConsumer<T> consumer = build();
        stream.sequential().forEach(consumer);
        return null;
    }

    private ForkingStreamConsumer<T> build() {
        List<BlockingQueue<T>> queues = new ArrayList<>();
        Map<Object, Future<?>> actions =
                forks.entrySet().stream().reduce(
                        new HashMap<Object, Future<?>>(),
                        (map, e) -> {
                            map.put(e.getKey(), getOperationResult(queues, e.getValue()));
                            return map;
                        },
                        (m1, m2) -> {
                            m1.putAll(m2);
                            return m1;
                        });
        return new ForkingStreamConsumer<>(queues, actions);
    }

    //创建future
    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
        BlockingQueue<T> queue = new LinkedBlockingDeque<>();
        queues.add(queue);
        Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);
        Stream<T> source = StreamSupport.stream(spliterator, false);
        return CompletableFuture.supplyAsync(
                () -> f.apply(source)
        );
    }
    //TODO
    public static void main(String...args){
        Dish dish1 = new Dish("bread",false,10,Type.OTHER);
        Dish dish2 = new Dish("fish",false,10,Type.MEAT);

//        List<Dish> menu = Lists.newArrayList(dish1,dish2);
//        Stream<Dish> menuStream = menu.stream();
//        StreamForker.Results results = new StreamForker<Dish>(menuStream)
//                .fork("shortMenu", s -> s.map(Dish::getName)
//                        .collect(joining(", ")))
//                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
//                .fork("mostCaloricDish", s -> s.collect(reducing(
//                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
//                        .get())
//                .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
//                .getResults();
    }

}
