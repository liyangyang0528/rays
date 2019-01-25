package com.lyyco.rays.service.concurrent.jcp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * page 112
 * Author liyangyang
 * 2019/1/25
 */
public class PrimeGenerator implements Runnable{
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;
    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }
    public void cancel(){
        cancelled = true;
    }
    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }
}
