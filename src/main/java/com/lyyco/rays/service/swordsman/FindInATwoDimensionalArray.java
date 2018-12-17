package com.lyyco.rays.service.swordsman;

/**
 * 二维数组中的查找
 * Author liyangyang
 * 2018/12/17
 */
public class FindInATwoDimensionalArray {
    // 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按
    // 照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个
    // 整数，判断数组中是否含有该整数。
    public static boolean find(int[][] arr, int target) {

        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i][0] > target) {
                continue;
            }
            int[] oneArr = arr[i];
            int low = 0;
            int high = oneArr.length - 1;
            //二分查找
            while (low <= high) {
//                int mid = high - low + low / 2;//TODO 消除整数溢出写错了！
                int mid = low + (high - low) / 2;
                if (target == oneArr[mid]) {
                    return true;
                }
                if (target > oneArr[mid]) {
                    low = mid + 1;
                }
                if (target < oneArr[mid]) {
                    high = mid - 1;
                }
            }
            //普通循环查找
//            for (int j = 0; j < oneArr.length - 1; i++) {
//                if(oneArr[j] == target){
//                    return true;
//                }
//            }

        }
        return false;
    }

    /**
     * !二维数组用行、列两个下标表示位置，可以只循环一次
     * @param arr
     * @param rows
     * @param columns
     * @param target
     * @return
     */
    public static boolean findByBook(int[][] arr, int rows, int columns, int target) {
        if (null == arr || rows <= 0 || columns <= 0) {
            return false;
        }
        boolean result = false;
        int row = 0;
        int column = columns - 1;
        while (row < rows && column >=0){
            if(arr[row][column] == target){
                result = true;
                break;
            }
            if(arr[row][column] > target){
                --column;
            }else {
                ++row;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(0 + (3 - 0) / 2);
    }
}
