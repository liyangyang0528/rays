package com.lyyco.rays.service.algorithm;


/**
 * 选择排序
 * In iteration i, find index min of smallest remaining entry
 * Swap a[i] and a[min].
 * com.lyyco.rays.service.algorithm
 * @Author liyangyang
 * 2018/3/27
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
                exch(a, i, min);
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
        Selection.sort(a);
    }

}
