package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 后台进程在不执行finally子句的情况下就会终止其run方法
 * Author liyangyang
 * 2018/12/26
 */
public class ADaemon implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("starting ADaemon");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("Exiting via interruptedException");
        }finally {
            System.out.println("this should always run?");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
        TimeUnit.SECONDS.sleep(1);
    }
}
