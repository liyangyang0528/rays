package com.lyyco.rays.service.swordsman;

import java.util.Stack;

/**
 * 从尾到头打印链表
 * Author liyangyang
 * 2019/1/7
 */
// 题6：从尾到头打印链表
// 题目：输入一个链表的头结点，从尾到头反过来打印出每个结点的值。
public class PrintListInReversedOrder {
    private static class Node {
        int value;
        Node next;
    }

    public void print(Node head) {
        Stack stack = new Stack();
        while (null != head.next) {
            stack.push(head.value);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public void printRecursively(Node head) {
        if (head != null) {
            if (head.next != null) {
                printRecursively(head.next);
            }
            System.out.println(head.value);
        }
    }

}
