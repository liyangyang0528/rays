package com.lyyco.rays.service.swordsman;

/**
 * 找出数组中重复的数字
 * Author liyangyang
 * 2018/12/20
 */
public class FindDuplication {
    // 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。
    // 数组中某些数字是重复的，但不知道有几个数字重复了，
    // 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    // 例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
    // 那么对应的输出是重复的数字2或者3。
    public int findUseOtherArray(int[] arr) {
        int[] indexArr = new int[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            if (indexArr[i] >= 1) {
                return i;
            }
            indexArr[i]++;
        }
        return -1;
    }

    public int find(int[] arr) {
        int dst = -1;
        int i = 0;
        while (i <= arr.length - 1) {
            int index = arr[i];
            if (arr[index] != index) {
                //swap
                int tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            } else {
                /*
                TODO 此处遇到问题
                在什么时候将i+1
                且，遇到重复数字时能判断出来
                 */

            }
        }
        return dst;
    }
    public int findByBook(int[]arr){
        for(int i = 0;i<arr.length;i++){
            //! 当第i个元素不等于i时
            while(arr[i] != i){
                int index = arr[i];
                if(arr[i] == arr[index]){
                    return arr[i];
                }
                //swap
                int tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
        }
        return -1;
    }

}
