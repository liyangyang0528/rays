package com.lyyco.rays.service.algorithm;

/**
 * 插入排序
 * com.lyyco.rays.service.algorithm
 * In iteration i, swap a[i] with each larger entry to its left
 * @Author liyangyang
 * 2018/3/27
 */
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else break;
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String...args){
        Integer [] a = new Integer[]{7,10,5,3,8,4,2,9,6};
        Insertion.sort(a);
    }
}
