package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * 线程本地存储
 * Author liyangyang
 * 2018/12/12
 */
public class ThreadLocalVariableHolder {
    private static class Accessor implements Runnable{
        private final int id;
        public Accessor(int idn){
            id = idn;
        }
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                ThreadLocalVariableHolder.increment();
                System.out.println(this);
                Thread.yield();
            }
        }
        public String toString(){
            return "#" + id + ":" + ThreadLocalVariableHolder.get();
        }
    }
    private static ThreadLocal<Integer> value = new ThreadLocal<>();
    private static void increment() {
        
    }
}
