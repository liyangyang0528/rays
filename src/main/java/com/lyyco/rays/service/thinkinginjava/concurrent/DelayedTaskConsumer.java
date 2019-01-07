package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.DelayQueue;

/**
 * Author liyangyang
 * 2019/1/7
 */
public class DelayedTaskConsumer implements Runnable{
    private DelayQueue<DelayedTask> q;
    public DelayedTaskConsumer(DelayQueue<DelayedTask> q){
        this.q = q;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                //run task with the current thread
                q.take().run();
            }
        } catch (InterruptedException e) {
            //acceptable way to exit
            System.out.println("finished delayed task consumer");
        }
    }
}
