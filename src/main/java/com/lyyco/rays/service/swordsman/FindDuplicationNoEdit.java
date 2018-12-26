package com.lyyco.rays.service.swordsman;

/**不修改数组找出重复的数字
 * Author liyangyang
 * 2018/12/20
 */
public class FindDuplicationNoEdit {
    // 题目：在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至
    // 少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的
    // 数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的
    // 输出是重复的数字2或者3。
    public int find(int[]arr){

        int[] indexArr = new int[arr.length];
        indexArr[0] = 0;
        for(int i = 0;i<arr.length;i++){
            int current = arr[i];
            if(indexArr[current] >=1){
                return current;
            }
            indexArr[current]++;
        }
        return -1;
    }
}
