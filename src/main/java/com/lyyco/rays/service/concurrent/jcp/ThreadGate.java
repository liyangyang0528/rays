package com.lyyco.rays.service.concurrent.jcp;

/**
 * 使用wait和notifyAll来实现可重新关闭的阀门
 * Author liyangyang
 * 2018/12/20
 */
public class ThreadGate {
    private boolean isOpen;
    private int generation;
    public synchronized void close(){
        isOpen = false;
    }
    public synchronized void open(){
        ++generation;
        isOpen = true;
        notifyAll();
    }
    //一直阻塞直到阀门被打开
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation){
            wait();
        }
    }
}
