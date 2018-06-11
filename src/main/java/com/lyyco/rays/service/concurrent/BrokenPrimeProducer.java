package com.lyyco.rays.service.concurrent;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 不可靠的取消操作将把生产者置于阻塞的操作中
 * Author liyangyang
 * 2018/6/11
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException e) {
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>();
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(primes);
        primeProducer.start();
        try {
            while (needMorePrimes())
                consume(primes.take());
        } finally {
            primeProducer.cancel();
        }
    }

    private void consume(BigInteger take) {

    }

    private boolean needMorePrimes() {
        return true;
    }
}
