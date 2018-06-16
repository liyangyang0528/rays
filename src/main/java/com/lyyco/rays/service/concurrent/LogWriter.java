package com.lyyco.rays.service.concurrent;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * JCP p125
 * 不支持关闭的生产者-消费者日志服务
 * Author liyangyang
 * 2018/6/16
 */
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    public LogWriter(Writer writer){
        this.queue = new LinkedBlockingQueue<>(10);
        this.logger = new LoggerThread(writer);
    }
    public void start(){
        logger.start();
    }
    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }


    private class LoggerThread extends Thread{
        private final PrintWriter writer;
        public LoggerThread(Writer writer){
            this.writer = (PrintWriter) writer;

        }
        public void run(){
            while (true){
                try {
                    writer.println(queue.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    writer.close();
                }
            }
        }
    }
}
