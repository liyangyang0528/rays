package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用LOCK锁
 * ReentrantLock允许尝试获取但最终未获取锁
 * Author liyangyang
 * 2018/12/12
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed(){
        /*
        就算锁是公平锁，tryLock()仍然不是公平的
        只要锁能够被获取，不管是否有其他线程在等待，当前线程都会获得锁
        如果想继承锁的公平性，
        使用tryLock(long, TimeUnit) tryLock(0, TimeUnit.SECONDS)
        如果想在等待时间内允许不公平获取，则可以结合两种方法
        if (lock.tryLock() ||
          lock.tryLock(timeout, unit)) {
        ...
        }}
         */
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock():" + captured);
        }finally {
            if(captured){
                lock.unlock();
            }
        }
    }
    public void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        try{
            System.out.println("tryLock(2,TimeUnit.SECONDS): " + captured);
        }finally {
            if(captured){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();//true --- lock is available
        al.timed();//true --- lock is available
        //create a separate task to grab the lock
        new Thread(){
            {
                setDaemon(true);
            }
            public void run(){
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();//give the 2nd task a chance
        al.untimed();//false --- lock grabbed by task
        al.timed();//false --- lock grabbed by task
    }
}
