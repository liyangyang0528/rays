package com.lyyco.rays.service.leetcode;

/**
 * Author liyangyang
 * 2018/10/8
 */
public class BinaryTreeInvert {
    //定义二叉树节点对象
    private static class Node {
        int val;
        Node left;
        Node right;
        Node(int value) {
            val = value;
        }
    }
    //采用递归方式进行反转
    public Node invertBinaryTree(Node root) {
        if (null == root) {
            return null;
        }
        //反转后的树对象
        Node invertedNode = new Node(root.val);
        invertedNode.left = invertBinaryTree(root.right);
        invertedNode.right = invertBinaryTree(root.left);
        return invertedNode;
    }
    //测试
    public static void main(String... args) {
        Node root = new Node(4);
        Node secondLeft = new Node(2);
        Node secondRight = new Node(7);
        Node thirdLeft = new Node(1);
        Node thirdLeft2 = new Node(6);
        Node thirdRight = new Node(3);
        Node thridRight2 = new Node(9);
        secondLeft.left = thirdLeft;
        secondLeft.right = thirdRight;
        secondRight.left = thirdLeft2;
        secondRight.right = thridRight2;
        root.left = secondLeft;
        root.right = secondRight;
        BinaryTreeInvert binaryTreeInvert = new BinaryTreeInvert();
        Node invertedBinaryTree = binaryTreeInvert.invertBinaryTree(root);
    }
}
