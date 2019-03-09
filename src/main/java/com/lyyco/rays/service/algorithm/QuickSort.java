package com.lyyco.rays.service.algorithm;

//import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 * Author liyangyang
 * 2018/3/28
 */
public class QuickSort {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * 找到拆分的元素
     * This code partitions on the item v in a[lo]. The main loop exits when the scan indices i and j cross.
     Within the loop, we increment i while a[i] is less than v and decrement j while a[j] is greater than
     v, then do an exchange to maintain the invariant property that no entries to the left of i are greater
     than v and no entries to the right of j are smaller than v. Once the indices meet, we complete the
     partitioning by exchanging a[lo] with a[j] (thus leaving the partitioning value in a[j])
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        //Partition into a[lo....i-1],a[i],a[i+1....hi]
        int i = lo, j = hi + 1;//left and right scan indices
        Comparable v = a[lo];//partitioning item
        while (true) {
            //find item on left to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }
            //find item on right to swap
            while (less(v, a[--j])) {
                if (j == lo) break;
            }
            //check if pointers cross swap
            if (i >= j) break;
            //当扫描索引交叉时，为完成分区过程需要做的所有事情是将分区项a [lo]与左子阵列（a [j]）的最右边的项交换并返回其索引j
            exch(a,i,j);
        }
        //swap with partitioning item; put v = a[j] into position
        exch(a,lo,j);
        //return index of item now known to be in place
        //with a[lo....j-1] <= a[j] <= a[j+1....hi]
        return j;
    }

    public static void sort(Comparable[] a){
        //shuffle needed for performance guarantee (stay tuned)
//        StdRandom.shuffle(a);
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
        String [] b = new String[]{"K","R","A","T","E","L","E","P","U","I","M","Q","C","X","O","S"};
        QuickSort.sort(b);
    }

}
