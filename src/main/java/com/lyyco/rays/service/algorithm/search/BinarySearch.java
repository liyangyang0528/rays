package com.lyyco.rays.service.algorithm.search;

public class BinarySearch {

    public static int search(int key, int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == key){
                return nums[mid];
            }
            if(nums[mid] > key){
//                high = mid;//TODO
                high = mid + 1;
            }else {
//                low = mid;//TODO
                low = mid -1;
            }
        }

        return -1;
    }
}
