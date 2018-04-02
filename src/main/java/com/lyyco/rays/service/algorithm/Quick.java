package com.lyyco.rays.service.algorithm;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 * Author liyangyang
 * 2018/3/28
 */
public class Quick {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            //find item on left to swap
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            //find item on right to swap
            while (less(a[lo], a[--j]))
                if (j == lo) break;
            //check if pointers cross swap
            if (i >= j) break;
            exch(a,i,j);
        }
        //swap with partitioning item
        exch(a,lo,j);
        //return index of item now known to be in place
        return j;
    }

    public static void sort(Comparable[] a){
        //shuffle needed for performance guarantee (stay tuned)
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);

    }
    public static void main(String...args){
        Integer [] a = new Integer[]{7,10,5,3,8,4,2,9,6};
        Quick.sort(a);
    }

}
