package com.lyyco.rays.service.concurrent.jcp;

import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用countDownLatch来启动和停止线程
 * Author liyangyang
 * 2018/10/19
 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        //起始门：初始值为1，所有线程都在这上面等待
        final CountDownLatch startGate = new CountDownLatch(1);
        //结束门
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        //启动门使得主线程能够同时释放所有工作线程
        startGate.countDown();
        //结束门使得主线程能够等待最后一个线程执行完成
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
