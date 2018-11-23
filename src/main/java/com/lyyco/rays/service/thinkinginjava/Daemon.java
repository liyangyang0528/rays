package com.lyyco.rays.service.thinkinginjava;

import java.util.concurrent.TimeUnit;

/**
 * Author liyangyang
 * 2018/11/23
 */
public class Daemon implements Runnable {
    private Thread[] t = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn  " + i + "started,  ");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.println();
        }
        while (true) {
            Thread.yield();
        }
    }

    private class DaemonSpawn implements Runnable {
        @Override
        public void run() {
            while (true) {
                Thread.yield();
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon()=" + d.isDaemon() + ",");
        TimeUnit.SECONDS.sleep(1);
    }
}
