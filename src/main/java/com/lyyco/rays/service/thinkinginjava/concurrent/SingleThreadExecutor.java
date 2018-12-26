package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author liyangyang
 * 2018/12/26
 */
public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
