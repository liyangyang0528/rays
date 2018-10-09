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
    //链表节点
    private static class ListNode{
        int value;
        ListNode next;
    }
    //采用递归方式进行反转
    public static Node invertBinaryTree(Node root) {
        if (null == root) {
            return null;
        }
        //反转后的树对象
        Node invertedNode = new Node(root.val);
        invertedNode.left = invertBinaryTree(root.right);
        invertedNode.right = invertBinaryTree(root.left);
        return invertedNode;
    }
    public static ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (null != curr){
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    //测试
    public static void main(String... args) {
//        Node root = new Node(4);
//        Node secondLeft = new Node(2);
//        Node secondRight = new Node(7);
//        Node thirdLeft = new Node(1);
//        Node thirdLeft2 = new Node(6);
//        Node thirdRight = new Node(3);
//        Node thridRight2 = new Node(9);
//        secondLeft.left = thirdLeft;
//        secondLeft.right = thirdRight;
//        secondRight.left = thirdLeft2;
//        secondRight.right = thridRight2;
//        root.left = secondLeft;
//        root.right = secondRight;
//        Node invertedBinaryTree = BinaryTreeInvert.invertBinaryTree(root);
        ListNode head = new ListNode();
        head.value = 1;
        head.next.value = 2;
        head.next.next.value = 3;
        BinaryTreeInvert.reverseList(head);
    }
}
