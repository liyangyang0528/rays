package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 终结任务 page692 终结任务
 * Author liyangyang
 * 2018/12/13
 */
public class OrnamentalGarden {
    static class Count {
        private int count = 0;
        private Random rand = new Random(47);
        //remove the synchronized keyword to see counting fail;
        public synchronized int increment() {
            int temp = count;
            if (rand.nextBoolean()) {
                Thread.yield();
            }
            return (count = ++temp);
        }
        public synchronized int value(){
            return count;
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new Entrance(i));
        }
        //run for a while,then stop and collect the data
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        if(!exec.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("Some tasks were not terminated!");
        }
        System.out.println("Total: " + Entrance.getTotalCount());
        System.out.println("Sum of Entrances: "+ Entrance.sumEntrances());

    }

}
