package com.lyyco.rays.service.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java并发包下的锁
 * Created by lyy on 2018/1/27.
 */
public class LockDemo {
    Lock locks = new ReentrantLock();

}
