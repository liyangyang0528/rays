package com.lyyco.rays.service.concurrent.jcp;

/**
 * 模拟CAS操作
 * Author liyangyang
 * 2018/12/24
 */
public class SimulatedCAS {
    private int value;
    public synchronized int get(){
        return value;
    }
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;
        if(oldValue == expectedValue){
            value = newValue;
        }
        return oldValue;
    }
    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return (expectedValue == compareAndSwap(expectedValue,newValue));
    }
}
