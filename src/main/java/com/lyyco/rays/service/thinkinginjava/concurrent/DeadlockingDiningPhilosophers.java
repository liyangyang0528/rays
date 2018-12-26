package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家就餐
 * Author liyangyang
 * 2018/12/26
 */
public class DeadlockingDiningPhilosophers {


    public static void main(String[] args) throws InterruptedException, IOException {
        int ponder = 2;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(sticks[i],sticks[(i+1)%size],i,ponder));
        }
        if(args.length == 3 && args[2].equals("timeout")){
            TimeUnit.SECONDS.sleep(5);
        }else {
            System.out.println("Press Enter to quit");
            System.in.read();
        }
        exec.shutdown();

    }
}
