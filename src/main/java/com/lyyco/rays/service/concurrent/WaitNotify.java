package com.lyyco.rays.service.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lyyco on 2018/1/10.
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    static class Wait implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while(flag){
                    System.out.println(Thread.currentThread() + "flag is true wait@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false.running @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }

        }
    }
    static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + "hold lock notify @"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notify();
                flag = false;

            }
            synchronized (lock){
                System.out.println(Thread.currentThread() + "hold lock again sleep @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
}
