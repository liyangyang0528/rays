package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DelayQueue是一个无界的BlockingQueue
 * 用于放置实现了Delayed接口的对象
 * 其中的对象只能在到期时才能从队列取走
 * 队列是有序的，队头对象的延迟到期时间最长
 * 如果没有任何延迟到期，就不会有任何头元素，poll()将返回null
 * Author liyangyang
 * 2019/1/7
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        //fill with tasks that have random delays
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(rand.nextInt(5000)));
            //set the stopping point
        }
        queue.add(new DelayedTask.EndSentinel(5000, exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}
