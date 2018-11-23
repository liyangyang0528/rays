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
            h = h / 3;

        }

        return a;
    }

    public int binarySearch(int[] a, int target) {
        if (null == a) {
            return 0;
        }
        int length = a.length;
        if (length < 0) {
            return 0;
        }
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int index = (high - low) / 2 + low;
            if (target == a[index]) {
                return index;
            }
            if (target > a[index]) {
                low = index + 1;
            }
            if (target < a[index]) {
                high = index - 1;
            }
        }
        return 0;
    }
    public static void rotate(int[] nums, int k) {
        if(null == nums || nums.length <0){
            return;
        }
        int low = 0;
        int high = nums.length-1;
        int kk = k;
        int loww = low;
        while(loww < kk){
            int tmp = nums[kk];
            nums[kk] = nums[loww];
            nums[loww] = tmp;
            loww++;
            kk--;
        }
        int kkk = k;
        int highh = high;
        while(kkk+1 < highh){
            int tmpp = nums[highh];
            nums[highh] = nums[kkk+1];
            nums[kkk+1] = tmpp;
            kkk++;
            highh--;
        }
        while(low < high){
            int tmppp = nums[high];
            nums[high] = nums[low];
            nums[low] = tmppp;
            low++;
            high--;
        }
    }
    public void rotateBySolution(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    public static void main(String[]args){
        int[] nums = {1,2,3,4,5,6,7};
        SortWrittenMyself.rotate(nums,3);
    }
}
