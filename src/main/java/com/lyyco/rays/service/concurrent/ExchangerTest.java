package com.lyyco.rays.service.concurrent;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author liyangyang
 * 2018/5/15
 */
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String...args){
        threadPool.execute(()->{
            String A = "银行流水A";
            try {
                exgr.exchange(A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(()->{
            String B = "银行流水B";
            try {
                String A = exgr.exchange(B);
                System.out.println("数据是否一致"+A.equals(B)+"A是："+A +"B是："+B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.shutdown();
    }


}
