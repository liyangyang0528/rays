package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在ReentrantLock上阻塞的任务具备可以被中断的能力
 * Author liyangyang
 * 2018/12/17
 */
public class BlockedMutex {
    private Lock lock = new ReentrantLock();
    public BlockedMutex(){
        //acquire it right away,to demonstrate interruption of a task blocked on a reentrantlock
        //构造器中获取所创建对象上自身的lock，并且从不释放
        //因此如果试图从第二个任务中调用f()（不同于创建这个BlockedMutex的任务）
        //那么将会因Mutex不可获得而被阻塞
        lock.lock();
    }
    public void f(){
        try{
            //this will never be available to a second task
            lock.lockInterruptibly();
        }catch (InterruptedException e){
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }

    static class Blocked2 implements Runnable{
        BlockedMutex blocked = new BlockedMutex();
        @Override
        public void run() {
            System.out.println("waiting for f() in BlockedMutex");
            //在调用f()的地方停止
            blocked.f();
            System.out.println("Broken out of blocked call");
        }
    }

    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt() ");
        t.interrupt();
    }
}
