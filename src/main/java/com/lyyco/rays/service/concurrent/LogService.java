package com.lyyco.rays.service.concurrent;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * JCP -p 126
 * 向logwriter添加可靠的取消操作
 * Author liyangyang
 * 2018/6/16
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    private boolean isShutdown;
    private int reservations;

    public LogService(PrintWriter writer) {
        this.writer = writer;
        this.queue = new LinkedBlockingQueue<>(10);
        this.loggerThread = new LoggerThread(writer);
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
            loggerThread.interrupt();
        }
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalStateException();
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        public LoggerThread(PrintWriter writer) {
            this.writer = writer;

        }

        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.print(msg);
                    } catch (InterruptedException e) {
                        //TODO retry
                    }
                }
            } finally {
                writer.close();
            }
        }
    }
}
