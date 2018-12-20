package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * page 717
 * Author liyangyang
 * 2018/12/20
 */
public class Receiver implements Runnable {
    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getOut());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char) in.read() + ",");
            }
        } catch (IOException e) {
            System.out.println("Receiver read exception");
        }
    }

    public static void main(String[] args) throws Exception{
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdown();
    }
}
