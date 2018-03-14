package com.lyyco.rays.service.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch使用测试
 * com.lyyco.rays.service.concurrent
 *
 * @Author liyangyang
 * 2018/3/14
 */
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println
                        ("子线程" + Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                c.countDown();
            }
        }).start();

        new Thread(()->{
                System.out.println
                        ("子线程" + Thread.currentThread().getName()+"正在执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
            c.countDown();
        }).start();


        c.await();
        System.out.println("3");
    }

}

