package com.lyyco.rays.service.algorithm.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向队列
 * Programming Assignment 2: Deques and Randomized Queues
 * Author liyangyang
 * 2018/4/2
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private Node nul;
    private int size;

    public Deque() { // construct an empty deque
        nul = new Node(null, null, null);
        first = nul;
        last = nul;
        size = 0;
    }

    public boolean isEmpty() {// is the deque empty?

        return first.item == null && last.item == null;
    }

    public int size() { // return the number of items on the deque

        return size;
    }

    public void addFirst(Item item) {// add the item to the front
        if (null == item) throw new NullPointerException("can't add null to the deque!");
        Node newFirst = new Node(nul, first, item);
        if (isEmpty()) {
            last = newFirst;
            first = newFirst;
        } else {
            first.prev = newFirst;
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {// add the item to the end
        if (null == item) throw new NullPointerException("can't add null to the deque!");
        Node newLast = new Node(last, nul, item);
        if (isEmpty()) {
            last = newLast;
            first = newLast;
        } else {
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() { // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException("The deque is empty");
        Item item = first.item;
        first = first.next;
        //todo ...
        if (first.item == null) {
            last = nul;
        } else {
            first.prev = nul;
        }
        size--;
        return item;
    }

    public Item removeLast() {// remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException("The deque is empty");
        Item item = last.item;
        last = last.prev;
        //todo
        if (last.item == null) {
            first = null;
        } else {
            last.next = nul;
        }
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {// return an iterator over items in order from front to end
        return new DequeIterator();
    }

    public static void main(String[] args) {// unit testing (optional)

    }

    private class Node {
        private Node prev;
        private Node next;
        private Item item;

        Node(Node prev, Node next, Item item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There is no more items");
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operatior is unsupported!");
        }
    }
}
