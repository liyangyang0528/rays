package com.lyyco.rays.service.concurrent.jcp;

/**
 * 通过轮询与休眠来实现简单的阻塞
 * Author liyangyang
 * 2018/12/20
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V>{
    public SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }
    public void put(V v) throws InterruptedException {
        while (true){
            synchronized (this){
                if(!isFull()){
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(10);
        }
    }
    public V take() throws InterruptedException {
        while (true){
            synchronized (this){
                if(!isEmpty()){
                    return doTake();
                }
            }
            Thread.sleep(10);
        }
    }

}
