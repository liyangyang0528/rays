package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author liyangyang
 * 2019/1/7
 */
public class PrioritizedTaskProducer implements Runnable {
    private Random rand = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService exec;

    public PrioritizedTaskProducer(
            Queue<Runnable> q, ExecutorService e) {
            queue = q;
            exec = e;
    }

    @Override
    public void run() {
        //Unbounded queue; never blocks
        //Fill it up fast with random priorities;
        for(int i = 0;i<20;i++){
            queue.add(new PrioritizedTask(rand.nextInt(10)));
            Thread.yield();
        }
        //Trickle in highest-priority jobs
        try {
            for(int i =0;i<10;i++){
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            //add jobs,lowest priority first
            for(int i = 0;i<10;i++){
                queue.add(new PrioritizedTask(i));
            }
            //A sentinel to stop all the tasks
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished prioritized task producer");
    }
}
