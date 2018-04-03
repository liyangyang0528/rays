package com.lyyco.rays.service.algorithm;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * week2_test
 * Author liyangyang
 * 2018/4/3
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()){
            rq.enqueue(StdIn.readString());
        }
        while (k>0){
            System.out.println(rq.dequeue());
            k--;
        }
//        String strs = StdIn.readString();
//        StdRandom.shuffle(strs.split(""));
//        for (int i = 0; i < k; i++) {
//            rq.enqueue(strs.split("")[i]);
//        }
//        for (int i = 0; i < k; i++) {
//            StdOut.println(rq.dequeue());
//        }
    }
}
