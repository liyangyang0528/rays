package com.lyyco.rays.service.concurrent.jcp;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用AQS实现二元闭锁
 * Created by lyyco on 2018/12/20.
 */
public class OneShotLatch {
    private final Sync sync = new Sync();

    private class Sync extends AbstractQueuedSynchronizer{
        protected int tryAcquireShared(int ignored){
            return (getState() == 1) ? 1 : -1;
        }
        protected boolean tryReleaseShared(int ignored){
            setState(1);
            return true;
        }
    }
    public void signal(){
        sync.releaseShared(0);
    }
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }
}
