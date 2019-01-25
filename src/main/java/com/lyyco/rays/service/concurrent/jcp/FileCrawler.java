package com.lyyco.rays.service.concurrent.jcp;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * JCP page 75 第五章 基础构建模块
 * 桌面搜索应用程序中的生产者任务和消费者任务
 * Author liyangyang
 * 2019/1/24
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue queue, FileFilter fileFilter, File file) {
        this.fileQueue = queue;
        this.fileFilter = fileFilter;
        this.root = file;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (null != entries) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry)) {
                    fileQueue.put(entry);
                }
            }
        }
    }

    private boolean alreadyIndexed(File entry) {

        return false;
    }
}
