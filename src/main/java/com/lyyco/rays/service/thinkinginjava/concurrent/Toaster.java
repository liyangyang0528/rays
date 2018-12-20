package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 制作土司
 * Author liyangyang
 * 2018/12/20
 */
public class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);
    public Toaster(ToastQueue tq){toastQueue = tq;}
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");

    }
}
