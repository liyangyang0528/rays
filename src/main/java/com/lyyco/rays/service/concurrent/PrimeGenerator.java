package com.lyyco.rays.service.concurrent;

import org.assertj.core.util.Lists;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用volatile类型的域来保存取消状态
 * Author liyangyang
 * 2018/6/11
 */
public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes = Lists.newArrayList();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                System.out.println(p);
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }

    public static void main(String... args) throws InterruptedException {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();
        try {
            Thread.sleep(1000);
        } finally {
            primeGenerator.cancel();
        }
    }
}
