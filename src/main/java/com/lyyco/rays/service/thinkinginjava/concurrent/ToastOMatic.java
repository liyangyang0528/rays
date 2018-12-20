package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 土司-生产者消费者与队列
 * page 717
 * Author liyangyang
 * 2018/12/20
 */
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newFixedThreadPool(4);
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue,butteredQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.MILLISECONDS.sleep(20);
        exec.shutdown();
    }
}
