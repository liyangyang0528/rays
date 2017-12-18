package com.lyyco.rays.service.concurrent;

/**
 * 演示死锁发生的情况
 * Created by lyyco on 2017/12/18.
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[]args){
        new DeadLockDemo().deadLock();
    }
    private void deadLock(){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.print("1");
                    }
                }
            }
        });
        Thread t2  = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (B){
                        synchronized (A){
                            System.out.print("2");
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}

