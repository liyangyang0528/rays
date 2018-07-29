package com.lyyco.rays.service.algorithm.sort;

/**
 * 优先队列
 * Author liyangyang
 * 2018/7/28
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    Key max() {
        return null;
    }

    Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;//k的子节点位置
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    public  void sort(Comparable[] a){
        int N = a.length;
        for(int k = N/2;k>=1;k--){
            this.sink(k);
        }
        while (N>1){
        }
    }
    public static void main(String...args){

    }
}
