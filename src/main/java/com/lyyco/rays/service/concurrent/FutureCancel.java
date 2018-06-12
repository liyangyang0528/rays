package com.lyyco.rays.service.concurrent;

import java.util.concurrent.*;

/**
 * 通过Future来取消任务
 * Author liyangyang
 * 2018/6/12
 */
public class FutureCancel {

    private static final ScheduledExecutorService cancelExec =
            Executors.newScheduledThreadPool(4);

    /**
     * 在外部线程中安排中断
     *
     * @param r
     * @param timeout
     * @param unit
     */
    public static void timedRun1(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
        r.run();

    }

    /**
     * 在专门的线程中中断任务
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timedRun2(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null) {
                }
            }
        }
        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();

    }

    /**
     * 使用future来取消
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timedRun3(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = cancelExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            //任务将被取消
        } catch (ExecutionException e) {
            //如果在任务中抛出了异常，则重新抛出该异常
        } finally {
            //如果任务已经结束，执行取消操作不会带来任何影响
            //如果任务正在运行，将被中断
            task.cancel(true);
        }
    }

}
