package com.lyyco.rays.service.leetcode;

import java.util.*;

/**
 * 数组类型题目解答
 * Author liyangyang
 * 2018/7/9
 */
public class SubsetsSolution {

    List<List<Integer>> result;
    List<Integer> output;
    int[] nums;
    int current;

    //给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    public List<List<Integer>> subsets(int[] nums) {
        current = 0;
        result = new ArrayList<>();
        output = new LinkedList<>();
        this.nums = nums;
        backtracker();
        return result;
    }

    private void backtracker() {
        if (current == nums.length) {
            result.add(new LinkedList<Integer>(output));
            return;
        }

        //Include current Integer
        output.add(nums[current]);
        current++;
        backtracker();
        //Exclude current Integer
        output.remove(output.size() - 1);
        backtracker();
        current--;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) return result;
        for (int i = 0; i < nums.length; i++) {
            int loc = nums[i] > 0 ? nums[i] - 1 : nums[i] * (-1) - 1;
            if (nums[loc] > 0)
                nums[loc] *= -1;
            else
                result.add(loc + 1);
        }
        return result;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (0 == nums[i]) {
                count = 0;
            } else {
                count++;
                ans = Math.max(ans, count);

            }

        }
        for(int num : nums){
            if(1 == num){
                count ++;
                if(ans < count) ans = count;
            }else {
                count =0;
            }
        }
        return ans;
    }

    /**
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * @param nums
     */
    public void moveZeroes(Integer[] nums) {
        List<Integer> numList = new ArrayList<>(Arrays.asList(nums));
        int allLength = numList.size();
        Iterator<Integer> iterator = numList.iterator();
        while (iterator.hasNext()){
            if (0==iterator.next()){
                iterator.remove();
            }
        }
        int currentLength = numList.size();
        for(int i =0;i<allLength-currentLength;i++){
            numList.add(0);
        }

    }
    public void moveZeroes1(int[] nums) {
        int num_non_zeroes = 0;
        int swap;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap = nums[i];
                nums[i] = 0;
                nums[num_non_zeroes] = swap;
                num_non_zeroes++;
            }
        }
    }
    public void moveZeroes2(int[] nums){
        int left = 0;
        int right = left + 1;

        while (right < nums.length){

            if (nums[left] == 0 && nums[right] == 0){
                right++;
            } else if (nums[left] == 0 && nums[right] != 0){
                swap1(nums, left, right);
            } else {
                left ++;
                right++;
            }
        }
    }

    private void swap1 (int[]nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

    }

    public void moveZeroes3(int[] nums) {
        int j = 0;
        for (int i = 0;i<nums.length;i++){
            if (nums[i]!=0){
                nums[j]=nums[i];
                j++;
            }
        }
        for (int k = j;k<nums.length;k++){
            nums[k]=0;
        }
    }
    /**
     * 448. Find All Numbers Disappeared in an Array
     * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
     * some elements appear twice and others appear once.
     * Find all the elements of [1, n] inclusive that do not appear in this array.
     * Input:
     * [4,3,2,7,8,2,3,1]
     * Output:
     * [5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if(null == nums || nums.length == 0) return res;
        for(int i = 0;i<nums.length;i++){
            while (nums[i] != i+1 && nums[i] != nums[nums[i]-1])
                swap2(nums,i,nums[i]-1);
        }
        for(int i=0;i<nums.length;i++)
            if(nums[i]!=i+1) res.add(i+1);

        return res;
    }

    private void swap2(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String... args) {
        SubsetsSolution subsetsSolution = new SubsetsSolution();
        int[] nums = {4,3,2,7,8,2,3,1};
//        subsetsSolution.findDuplicates(nums);
//        subsetsSolution.findMaxConsecutiveOnes(nums);
//        subsetsSolution.moveZeroes1(nums);
        subsetsSolution.findDisappearedNumbers(nums);
    }
}
