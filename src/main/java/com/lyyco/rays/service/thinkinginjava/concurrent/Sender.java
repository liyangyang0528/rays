package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * page 717
 * 任务间通过管道进行输入
 * Author liyangyang
 * 2018/12/20
 */
public class Sender implements Runnable {
    private Random rand = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getOut() {
        return out;
    }

    @Override
    public void run() {
        try {

            while (true) {
                for (char c = 'A'; c <= 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (IOException e) {
            System.out.println("Sender write exception");
        } catch (InterruptedException e) {
            System.out.println("Sender sleep interrupted");
        }
    }
}
