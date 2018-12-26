package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * page 654 定义任务
 * Author liyangyang
 * 2018/12/6
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "). ";
    }

    @Override
    public void run() {

        while (countDown-- > 0) {
            System.out.println(status());
            /*
            对线程调度器的一种建议
            将CPU从一个线程转移给另一个线程
             */
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        LiftOff liftOff = new LiftOff();
//        liftOff.run();
        Thread t = new Thread(liftOff);
        t.start();
        System.out.println("Waiting for LiftOFF");
        for (int i = 0; i < 5; i++) {
            //main()创建Thread对象时，它并没有捕获任何对这些对象的引用
            //在使用普通对象时，这对垃圾回收来说是一场公平的游戏
            //但在使用Thread时，每个Thread都注册了它自己
            //因此确实有一个对它的引用，而且在它的任务退出其run()并死亡之前
            //垃圾回收器无法清除它
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
