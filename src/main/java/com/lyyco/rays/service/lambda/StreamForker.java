package com.lyyco.rays.service.lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;


/**
 * 定义一个StreamForker，对原始流进行封装，在一个流上执行多个操作
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
        try {
            stream.sequential().forEach(consumer);
        } finally {
            consumer.finish();
        }
        return consumer;
    }

    private ForkingStreamConsumer<T> build() {
        //创建由队列组成的列表，每一个队列对应一个操作
        List<BlockingQueue<T>> queues = new ArrayList<>();
        //建立用于标识操作的键 与 包含操作结果的Future之间的映射关系
        Map<Object, Future<?>> actions =
                forks.entrySet().stream().reduce(
                        new HashMap<Object, Future<?>>(),
                        (map, e) -> {
                            //每个Future都是通过getOperationResult方法创建的
                            map.put(e.getKey(), getOperationResult(queues, e.getValue()));
                            return map;
                        },
                        (m1, m2) -> {
                            m1.putAll(m2);
                            return m1;
                        });
        //
        return new ForkingStreamConsumer<>(queues, actions);
    }

    //创建future
    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
        //TODO 创建一个新队列，并添加到队列的列表中？此步骤有何作用？
        //-> 这新队列会被传递给一个新的BlockingQueueSpliterator对象，延迟绑定
        BlockingQueue<T> queue = new LinkedBlockingDeque<>();
        queues.add(queue);
        //创建一个自定义Spliterator，遍历列队中的元素
        Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);
        //创建一个流，将spliterator作为数据源
        Stream<T> source = StreamSupport.stream(spliterator, false);
        //创建一个Future对象，以异步方式计算在流上执行特定函数的结果
        return CompletableFuture.supplyAsync(
                () -> f.apply(source)
        );
    }

    //TODO
    public static void main(String... args) {
        Dish dish1 = new Dish("bread", false, 10, Type.OTHER);
        Dish dish2 = new Dish("fish", false, 10, Type.MEAT);

        List<Dish> menu = Lists.newArrayList(dish1, dish2);
        Stream<Dish> menuStream = menu.stream();
        Results results = new StreamForker<>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName)
                        .collect(joining(", ")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloricDish", s -> s.collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                        .get())
                .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
                .getResults();
        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        System.out.println(shortMenu);
        System.out.println(totalCalories);
        System.out.println(mostCaloricDish);
    }

    /**
     * 内部类
     *
     * @param <T>
     */
    static class ForkingStreamConsumer<T> implements Consumer<T>, Results {

        static final Object END_OF_STREAM = new Object();
        private final List<BlockingQueue<T>> queues;
        private final Map<Object, Future<?>> actions;

        ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
            this.queues = queues;
            this.actions = actions;
        }

        /**
         * 等待Future完成相关的计算，返回由特定键标识的处理结果
         *
         * @param key
         * @param <R>
         * @return
         */
        @Override
        public <R> R get(Object key) {
            try {
                return ((Future<R>) actions.get(key)).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * 将流中遍历的元素添加到所有的队列中
         * 每当ForkingStreamConsumer接受流中的一个元素，它就会将该元素添加到BlockingQueues中
         * 当原始流中的所有元素都添加到所有队列后,finish方法会将最后一个END_OF_STREAM元素添加到所有队列
         * BlockingQueueSpliterators碰到最后这个END_OF_STREAM元素时就知道队列中不再有需要处理的元素了。
         *
         * @param t
         */
        @Override
        public void accept(T t) {
            queues.forEach(q -> q.add(t));
        }

        /**
         * 将最后一个元素添加到队列中，表明该流已经结束
         */
        void finish() {
            accept((T) END_OF_STREAM);
        }
    }

    /**
     * 内部类
     * @param <T>
     */
    class BlockingQueueSpliterator<T> implements Spliterator<T> {

        private final BlockingQueue<T> queue;

        public BlockingQueueSpliterator(BlockingQueue<T> queue) {
            this.queue = queue;
        }

        /**
         * 从BlockingQueue中取得原始流中的元素
         * 而这些元素最初由ForkingSteamConsumer添加
         *
         * @param action
         * @return
         */
        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            T t;
            while (true) {
                try {
                    t = queue.take();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (t != com.lyyco.rays.service.lambda.ForkingStreamConsumer.END_OF_STREAM) {
                action.accept(t);
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }

}
