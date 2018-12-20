package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * 给土司摸黄油
 * Author liyangyang
 * 2018/12/20
 */
public class Butterer implements Runnable {
    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dry, ToastQueue buttered) {
        dryQueue = dry;
        butteredQueue = buttered;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted ");
        }
        System.out.println("Butterer off");

    }
}
