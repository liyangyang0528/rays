package com.lyyco.rays.service.jcp;

/**
 * 基于CAS实现非阻塞计数器
 * Author liyangyang
 * 2018/11/25
 */
public class CasCounter {
    private SimulatedCAS value;
    public int getValue(){
        return value.get();
    }
    public int increment(){
        int v;
        do{
            v = value.get();
        }
        while (v != value.compareAndSwap(v,v+1));
        return v+1;
    }
}


