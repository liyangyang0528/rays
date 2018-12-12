package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不能捕获从线程中逃逸的异常
 * 一旦异常逃出任务的run()方法，它就会向外传播到控制台
 * Author liyangyang
 * 2018/12/12
 */
public class ExceptionThread implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        }catch (RuntimeException rue){
            System.out.println("catch");
        }
    }
}
