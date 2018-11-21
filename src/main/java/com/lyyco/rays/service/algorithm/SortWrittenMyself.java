package com.lyyco.rays.service.algorithm;

/**
 * Author liyangyang
 * 2018/11/21
 */
public class SortWrittenMyself {
    public int[] selectSort(int[] a) {
        int length = a.length;
        if (null == a || a.length < 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if(a[j] < a[min]){
                    min = j;
                }
            }
            //交换
            int tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
        }
        return a;
    }

    public int[] insertSort(int[] a){
        int length = a.length;
        if (null == a || a.length < 0) {
            return null;
        }
        return null;
    }
}
