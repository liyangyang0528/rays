package com.lyyco.rays.service.concurrent.jcp;

/**page 240
 * 将前提条件的失败传递给调用者
 * Author liyangyang
 * 2018/12/20
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{
    public GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }
    public synchronized void put(V v){
        if(isFull()){
            throw new RuntimeException();
        }
        doPut(v);
    }
    public synchronized V take() {
        if(isEmpty()){
            throw new RuntimeException();
        }
        return doTake();
    }
}
