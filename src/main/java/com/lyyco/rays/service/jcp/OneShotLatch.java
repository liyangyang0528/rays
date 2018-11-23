package com.lyyco.rays.service.jcp;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用AQS实现Latch
 * Author liyangyang
 * 2018/11/23
 */
public class OneShotLatch {
    private final Sync sync = new Sync();
    public void signal(){
        sync.releaseShared(0);
    }
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer{
        protected int tryAcquiredShared(int ignored){
            return (getState() == 1)? 1 : -1;
        }
        protected boolean tryReleaseShared(int ignored){
            setState(1);
            return true;
        }
    }
}
