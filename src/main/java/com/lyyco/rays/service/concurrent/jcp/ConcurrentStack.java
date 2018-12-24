package com.lyyco.rays.service.concurrent.jcp;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用Treiber算法的非阻塞栈
 * Author liyangyang
 * 2018/12/24
 */
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<>();

    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }
}
