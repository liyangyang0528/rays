package com.lyyco.rays.service.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池
 * Created by lyy on 2018/1/26.
 */
public class ThreadPoolDemo {


    public void threadPool() {
        /*
        创建一个固定大小的线程池
        超出的线程会在LinkedBlockingQueue队列等待
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        /*
        创建一个可缓存的无界线程池
        当任务超过线程池的线程数则创建新线程
        当线程池中的线程空闲时间超过60s会自动回收该线程
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        /*
        创建只有一个线程的线程池
        所有任务保存在LinkedBlockingQueue中
        等待单一线程按照指定顺序来执行任务
         */
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        for(int i = 0; i<6;i++) {
            final int index = i;
            singleThreadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+", index="+index);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        /*
        创建一个可定时执行或周期执行任务的线程池
        可指定线程池的核心线程个数
         */
        ScheduledExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(3);
        //定时执行一次任务-延迟1s
        scheduleThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", delay is 1s");
            }
        },1, TimeUnit.SECONDS);
        //周期性执行任务，每3s一次,延迟2s
        scheduleThreadPool.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName() + ", every 3s");
        },2,3,TimeUnit.SECONDS);

        /*
        创建线程池
         */
        LinkedBlockingDeque blockingDeque = new LinkedBlockingDeque(100);
        ThreadPoolExecutor threadPoolExecutors = new ThreadPoolExecutor(5,10,
                1000L,TimeUnit.MINUTES,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build());
        threadPoolExecutors.execute(()->{

        });
    }
public static void main(String[]args){
        ThreadPoolDemo demo = new ThreadPoolDemo();
        demo.threadPool();
}

}
