package com.lyyco.rays.service.thinkinginjava.concurrent;

/**给抹过黄油的土司抹过果酱
 * Apply jam to buttered toast
 * Author liyangyang
 * 2018/12/20
 */
public class Jammer implements Runnable{
    private ToastQueue butteredQueue,finishedQueue;
    public Jammer(ToastQueue buttered,ToastQueue finished){
        butteredQueue = buttered;
        finishedQueue = finished;
    }
    @Override
    public void run() {
        try {

            while (!Thread.interrupted()) {
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}
