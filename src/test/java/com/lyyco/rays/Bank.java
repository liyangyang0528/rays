package com.lyyco.rays;

/**
 * Author liyangyang
 * 2019/1/19
 */

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private final double[] accounts;

    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int form, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[form] < amount) {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread().getName());
            accounts[form] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, form, to);
            accounts[to] += amount;
            sufficientFunds.signalAll();
            System.out.printf(" Total Balance: %10.2f\n", getTotalBalance());
        } finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {
        double sum = 0;

        bankLock.lock();
        try {
            for (double balance : accounts)
                sum += balance;
        } finally {
            bankLock.unlock();
        }

        return sum;
    }

    public int size() {
        return accounts.length;
    }

    private static final int ACCOUNT_NUMBER = 100;
    private static final double INITIAL_BALANCE = 100;
    private static final double MAX_AMOUNT = 100;
    private static final int DELAY = 100;

    public static void main(String[] args) {
        Bank bank = new Bank(ACCOUNT_NUMBER, INITIAL_BALANCE);

        for (int i = 0; i < bank.size(); i++) {
            new Thread(() -> {
                try {
                    while (true) {
                        int from = (int) (bank.size() * Math.random());
                    }
                } catch (Exception e) {
                }
            }).start();
        }
    }
}