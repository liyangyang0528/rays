package com.lyyco.rays.service.leetcode;

import java.util.PriorityQueue;

/**
 * Author liyangyang
 * 2018/10/15
 */
public class ArraySolution {
    public static int[] sortArrayByParity(int[] A) {
        for (int i = 0; i < A.length; i++) {
            //当前元素是奇数，则找到第一个偶数，与之交换位置
            if (A[i] % 2 == 1) {
                int tmp = A[i];
                for (int j = i + 1; j < A.length; j++) {
                    //找到第一个偶数的位置
                    if (A[j] % 2 == 0) {
                        //交换两个数的位置
                        A[i] = A[j];
                        A[j] = tmp;
                        break;
                    }
                }
            }
        }
        return A;
    }
    public static void main(String...args){
        int[] A = {3,1,2,4};
        ArraySolution.sortArrayByParity(A);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
    }
}
