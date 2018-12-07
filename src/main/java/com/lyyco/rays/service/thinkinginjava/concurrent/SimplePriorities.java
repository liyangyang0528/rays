package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * 线程优先级
 * Author liyangyang
 * 2018/12/6
 */
public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + "：" + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 1; i < 10000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if(i % 1000 == 0){
                    Thread.yield();
                }
            }
        }
    }
}
