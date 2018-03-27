package com.lyyco.rays.service.algorithm;

/**
 * 链表实现的栈
 * com.lyyco.rays.service.algorithm
 *
 * @Author liyangyang
 * 2018/3/27
 */
public class LinkedStackOfStrings {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

}
