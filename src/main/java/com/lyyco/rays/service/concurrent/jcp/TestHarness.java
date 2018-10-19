package com.lyyco.rays.service.concurrent.jcp;

import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用countDownLatch来启动和停止线程
 * Author liyangyang
 * 2018/10/19
 */
public class TestHarness {
    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for(int i =0;i<nThreads;i++){
            Thread t = new Thread(){
              public void run(){
                  try {
                      startGate.await();
                      try {
                          task.run();
                      }finally {
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
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end -start;
    }
}
