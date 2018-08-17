package com.lyyco.rays.service.leetcode;

/**
 * Author liyangyang
 * 2018/8/16
 */
public class TreeSolution {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //654. Maximum Binary Tree
    //The size of the given array will be in the range [1,1000]
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int l, int r) {
        if (l == r)
            return null;
        int max_i = max(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = construct(nums, 1, max_i);
        root.right = construct(nums, max_i + 1, r);
        return root;
    }

    private int max(int[] nums, int l, int r) {
        int max_i = 1;
        for (int i = 1; i < r; i++) {
            if (nums[max_i] < nums[i]) {
                max_i = i;
            }
        }
        return max_i;
    }
    //814. Binary Tree Pruning
    public TreeNode pruneTree(TreeNode root) {
        isZeroNode(root);
        return null;
    }

    private boolean isZeroNode(TreeNode root) {
        if(null == root) return true;
        if(0 == root.val && isZeroNode(root.left) && isZeroNode(root.right)){
            root = null;
            return true;
        }
//        if(1 == root.val && isZeroNode(root.left))
        return false;
    }
    public TreeNode pruneTreeOfficial(TreeNode root){
        return containsOne(root) ? root :null;
    }

    private boolean containsOne(TreeNode node) {
        if(node == null) return false;
        boolean a1 = containsOne(node.left);
        boolean a2 = containsOne(node.right);
        if(!a1)node.left = null;
        if(!a2)node.right = null;
        return node.val ==1 || a1 || a2;
    }
    //108. Convert Sorted Array to Binary Search Tree
    public TreeNode sortedArrayToBST(int[] nums) {
        return null;
    }

}
