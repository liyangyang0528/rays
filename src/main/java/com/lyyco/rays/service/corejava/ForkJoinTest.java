package com.lyyco.rays.service.corejava;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * fork join框架
 * work stealing工作密取
 * 每个工作线程都有一个双端队列deque来完成任务
 * 每个工作线程将子任务压入其双端队列的队头（只有一个线程可以访问队头，所以不需要加锁）
 * 另外的工作线程空闲时，它会从另一个双端队列的队尾密取一个任务
 * page 692
 * Author liyangyang
 * 2019/1/20
 */
public class ForkJoinTest {
    public static void main(String[] args) {

    }

    class Counter extends RecursiveTask<Integer> {
        public static final int THRESHOLD = 1000;
        private double[] values;
        private int from;
        private int to;
        private DoublePredicate filter;

        public Counter(double[] values, int from, int to, DoublePredicate filter) {
            this.values = values;
            this.from = from;
            this.to = to;
            this.filter = filter;
        }

        @Override
        protected Integer compute() {
            if (to - from < THRESHOLD) {
                int count = 0;
                for (int i = from; i < to; i++) {
                    if (filter.test(values[i])) {
                        count++;
                    }
                }
                return count;
            }else {
                int mid = (from + to)/2;
                Counter first = new Counter(values,from,mid,filter);
                Counter second = new Counter(values,mid,to,filter);
                //接收很多任务并阻塞，直到所有这些任务都已经完成
                invokeAll(first,second);
                //join方法将生成结果
                return first.join() + second.join();
            }
        }
    }

}
