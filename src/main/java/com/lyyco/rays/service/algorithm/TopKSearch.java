package com.lyyco.rays.service.algorithm;

public class TopKSearch {
    public static int findKth(int [] a,int n,int k){
        return findKth(a,0,n-1,k);
    }

    private static int findKth(int[] a, int start, int end, int k) {
        //先进行一次快排，取得枢纽
        int pivot = partation(a,start,end);
        if(k == pivot - start + 1){
            return a[pivot];
        }else if(k > pivot - start +1){
            return findKth(a,pivot + 1,end,k-pivot+start-1);
        }else {
            return findKth(a,start,pivot-1,k);
        }

    }

    public static int partation(int[] a,int low,int high){
        int key = a[low];
        while (low < high){
            while (low < high && a[high] <= key){
                high --;
            }
            a[low] = a[high];
            while (low < high && a[low] >= key){
                low ++;
            }
            a[high] = a[low];
        }
        a[low] = key;
        return low;
    }
}
