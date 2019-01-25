package com.lyyco.rays.service.concurrent.jcp;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * JCP page 75 第五章 基础构建模块
 * 桌面搜索应用程序中的生产者任务和消费者任务
 * Author liyangyang
 * 2019/1/24
 */
public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void indexFile(File take) {
    }

    public static void main(String[] args) {
        File[] roots = new File[10];
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(10);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        for (File root : roots) {
            new Thread(new FileCrawler(queue, fileFilter, root)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
