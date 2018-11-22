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
                if (a[j] < a[min]) {
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

    public int[] insertSort(int[] a) {
        int length = a.length;
        if (null == a || a.length < 0) {
            return null;
        }
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
            }
        }
        return null;
    }

    /**
     * 使数组中任意间隔为h的元素都是有序的，这样的数组称为h有序数组
     *
     * @param a
     * @return
     */
    public int[] shellSort(int[] a) {
        int N = a.length;
        int h = 1;
        //1,4,13,40,121,364,1093...
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            //将数组变为h有序
            for (int i = h; i < N; i++) {
                //将a[i]插入到a[i-h],a[i-2h],a[i-3h]...之中
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
                    int tmp = a[j];
                    a[j] = a[j - h];
                    a[j - h] = tmp;
                }
            }
            h = h/3;

        }

        return a;
    }
}
