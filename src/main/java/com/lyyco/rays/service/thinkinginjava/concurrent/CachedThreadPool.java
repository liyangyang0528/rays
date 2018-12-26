package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executor
 * Author liyangyang
 * 2018/12/26
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        //对shutdown()方法的调用可以防止新任务被提交给这个Executor
        //当前线程（驱动main()的线程）将继续运行在shutdown()被调用之前提交的所有任务
        exec.shutdown();
    }
}
