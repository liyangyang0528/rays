package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TIJ  page 736 21.7.7
 * Exchanger是在两个任务之间交换对象的栅栏
 * 当这些任务进入栅栏时，他们各自拥有一个对象
 * 当它们离开时，它们都拥有之前由对象持有的对象（？？？没理解这句话）
 * 典型应用场景是：一个任务在创建对象，这些对象的创建代价很昂贵
 * 而另一个任务在消费这些对象，通过这种方式，可以有更多的对象在被创建的同时被消费
 * Author liyangyang
 * 2019/1/8
 */
public class ExchangerDemo {
    static int size = 10;
    static int delay = 5;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<>();
        List<Fat>
                producerList = new CopyOnWriteArrayList<>(),
                consumerList = new CopyOnWriteArrayList<>();
        exec.execute(new ExchangerProducer<Fat>(xc,BasicGenerator.create(Fat.class),producerList));
        exec.execute(new ExchangerConsumer<Fat>(xc,consumerList));

    }

    static class ExchangerProducer<T> implements Runnable {
        private Generator<T> generator;
        private Exchanger<List<T>> exchanger;
        private List<T> holder;
        ExchangerProducer(Exchanger<List<T>> exchg,
            Generator<T> gen, List<T> holder) {
            exchanger = exchg;
            generator = gen;
            this.holder = holder;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    for (int i = 0; i < ExchangerDemo.size; i++) {
                        holder.add(generator.next());
                    }
                    //Exchange full for empty
                    holder = exchanger.exchange(holder);
                }
            } catch (InterruptedException e) {
                //ok to terminate this way
            }

        }
    }

    static class ExchangerConsumer<T> implements Runnable {
        private Exchanger<List<T>> exchanger;
        private List<T> holder;
        private volatile T value;
        ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
            exchanger = ex;
            this.holder = holder;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    holder = exchanger.exchange(holder);
                    for (T x : holder) {
                        value = x;//fetch out value
                        holder.remove(x);//ok for CopyOnWriteArrayList
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Final value: " + value);
        }
    }
}