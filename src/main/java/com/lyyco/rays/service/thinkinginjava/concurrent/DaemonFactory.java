package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 守护线程池
 * Author liyangyang
 * 2018/11/23
 */
public class DaemonFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
        return t;
    }

    public static void main(String...args){
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonFactory());
    }
}
