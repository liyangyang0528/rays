package com.lyyco.rays.service.jcp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jcp 267
 * 基于ReentrantLock实现随机数生成器
 * Author liyangyang
 * 2018/11/25
 */
public class ReentrantLockPseudoRandom {
    private final Lock lock = new ReentrantLock(false);
    private int seed;
    ReentrantLockPseudoRandom(int seed){
        this.seed = seed;
    }
    public int nextInt(int n){
        lock.lock();
        try{
            int s = seed;
            seed = calculateNext(s);
            int remainder = s % n;
            return remainder >0 ? remainder : remainder + n;
        }finally {
            lock.unlock();
        }
    }

    private int calculateNext(int s) {
        return 0;
    }
}
