package com.lyyco.rays.service.concurrent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java并发包下的锁
 * Created by lyy on 2018/1/27.
 */
public class LockDemo {
    Lock locks = new ReentrantLock();
    final HashMap<String,String> map = new HashMap<>(2);

    Thread t = new Thread(() -> {
        for(int i =0;i<1000;i++){
            map.put(UUID.randomUUID().toString(),"");
        }
    });



}
