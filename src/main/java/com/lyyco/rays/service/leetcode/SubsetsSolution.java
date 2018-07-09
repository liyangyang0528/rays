package com.lyyco.rays.service.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组类型题目解答
 * Author liyangyang
 * 2018/7/9
 */
public class SubsetsSolution {

    List<List<Integer>> result;
    List<Integer> output;
    int [] nums;
    int current;
    //给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    public List<List<Integer>> subsets(int[] nums) {
        current=0;
        result = new ArrayList<>();
        output = new LinkedList<>();
        this.nums = nums;
        backtracker();
        return result;
    }

    private void backtracker(){
        if(current==nums.length){
            result.add(new LinkedList<Integer>(output));
            return;
        }

        //Include current Integer
        output.add(nums[current]);
        current++;
        backtracker();
        //Exclude current Integer
        output.remove(output.size()-1);
        backtracker();
        current--;
    }
}
