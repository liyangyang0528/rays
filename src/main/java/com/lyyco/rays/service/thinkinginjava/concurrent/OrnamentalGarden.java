package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.*;
import java.util.concurrent.*;
/**
 * 终结任务 page692 终结任务
 * Author liyangyang
 * 2018/12/13
 */
public class OrnamentalGarden {
    static class Count {
        private int count = 0;
        private Random rand = new Random(47);

        public synchronized int increment() {
            int temp = count;
            if (rand.nextBoolean()) {
                Thread.yield();
            }
            return (count = ++temp);
        }
        public synchronized int value(){
            return count;
        }
    }

    public static void main(String[] args) {
    }

}
