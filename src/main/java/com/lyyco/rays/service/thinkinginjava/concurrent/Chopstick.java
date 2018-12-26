package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * 死锁  page 719
 * Author liyangyang
 * 2018/12/26
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void take() throws InterruptedException {
        while (taken){
            wait();
        }
        taken = true;
    }
    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
}
