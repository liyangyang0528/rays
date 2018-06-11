package com.lyyco.rays.service.concurrent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer类负责管理延迟任务
 * 但存在一些缺陷
 * 1.Timer在执行所有定时任务时只会创建一个线程，如果某个任务执行时间过长，会破坏其他TimerTask的定时精准性
 * 2.如果TimeTask抛出未检查的异常，将终止定时线程
 * 这种情况下，Timer不会恢复线程的执行
 * 而是错误地认为整个Timer都被取消了
 * 因此已经被调度但尚未执行的TimeTask不会再执行
 * 新的任务也不能被调度
 * Author liyangyang
 * 2018/6/11
 */
public class OutOfTime {
    /**
     * 实际情况是程序运行1秒就结束了
     * 并抛出“Timer already cancelled”
     * @param args
     * @throws InterruptedException
     */
    public static void main(String...args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(1000);
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(5000);
    }

    private static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
