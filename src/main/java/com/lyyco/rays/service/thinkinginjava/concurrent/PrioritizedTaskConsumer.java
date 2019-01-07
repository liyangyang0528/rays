package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Author liyangyang
 * 2019/1/7
 */
public class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> q;

    public PrioritizedTaskConsumer(
            PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Finished prioritized task consumer");
    }
}
