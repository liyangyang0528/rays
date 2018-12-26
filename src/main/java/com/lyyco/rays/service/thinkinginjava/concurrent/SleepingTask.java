package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程休眠
 * Author liyangyang
 * 2018/12/26
 */
public class SleepingTask extends LiftOff{
    public void run(){
        try {
            while (countDown -- > 0){
                System.out.println(status());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0;i<5;i++){
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
}
