package com.lyyco.rays.service.algorithm;

/**
 * 希尔排序
 * The worst-case number of compares used by shellsort
 * with the 3x+1 increments is O(N 3/2).
 * Author liyangyang
 * 2018/3/28
 */
public class Shell {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        //3x+1 increment sequence
        while (h < N / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            //insertion sort
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            //move to next increment
            h = h / 3;
        }
    }
}
