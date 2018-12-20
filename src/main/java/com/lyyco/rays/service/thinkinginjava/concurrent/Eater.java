package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * Author liyangyang
 * 2018/12/20
 */
public class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int count = 0;

    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishedQueue.take();
                if (t.getId() != count++ ||
                        t.getStatus() != Toast.Status.JAMMED) {
                    System.out.println(">>>>Error: " + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater Interrupted");
        }
        System.out.println("Eater off");
    }
}
