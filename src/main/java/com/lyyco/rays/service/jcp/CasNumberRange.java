package com.lyyco.rays.service.jcp;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过CAS来维持包含多个变量的不变性条件
 * Author liyangyang
 * 2018/11/25
 */
public class CasNumberRange {
    private static class IntPair{
        final int lower;
        final int upper;
        public IntPair(int lower,int upper){
            this.lower = lower;
            this.upper = upper;
        }
    }
    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(00,0));

    public void setLower(int i){
        while (true){
            IntPair oldv = values.get();
            if(i > oldv.upper){
                throw new RuntimeException();
            }
            IntPair newv = new IntPair(i,oldv.upper);
            if(values.compareAndSet(oldv,newv)){
                return;
            }
        }
    }
}
