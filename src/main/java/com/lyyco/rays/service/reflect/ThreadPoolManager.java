package com.lyyco.rays.service.reflect;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 三种访问方式
 * Author liyangyang
 * 2018/10/23
 */
public class ThreadPoolManager {
    private final ScheduledExecutorService stpe = Executors.newScheduledThreadPool(2);

}
