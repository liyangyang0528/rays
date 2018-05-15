package com.lyyco.rays.service.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Author liyangyang
 * 2018/5/15
 */
public class CyclicBarrierTest {

    private static CyclicBarrier c = new CyclicBarrier(2);
    /**
     * 第二个构造器；在线程到达屏障时，优先执行barrierAction--Runnable
     */
    private static CyclicBarrier c2 = new CyclicBarrier(2,
            ()->{System.out.println(3);});


    public static void main(String... args) {
        new Thread(() -> {
            try {
                //告诉cyclicbarrier已经到达了屏障，当前线程被阻塞
//                c.await();
                c2.await();
                System.out.println(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        try {
//            c.await();
            c2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);

    }
}
