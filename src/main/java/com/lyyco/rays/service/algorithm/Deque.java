package com.lyyco.rays.service.algorithm;

import java.util.Iterator;

/**
 * 双向队列
 * Programming Assignment 2: Deques and Randomized Queues
 * Author liyangyang
 * 2018/4/2
 */
public class Deque<Item> implements Iterable<Item> {
    public Deque(){ // construct an empty deque

    }
    public boolean isEmpty(){// is the deque empty?

        return false;
    }

    public int size(){ // return the number of items on the deque

        return 0;
    }

    public void addFirst(Item item){// add the item to the front

    }
    public void addLast(Item item) {// add the item to the end

    }
    public Item removeFirst(){ // remove and return the item from the front

        return null;
    }

    public Item removeLast(){// remove and return the item from the end

        return null;
    }

    @Override
    public Iterator<Item> iterator() {// return an iterator over items in order from front to end
        return null;
    }
    public static void main(String[] args){// unit testing (optional)

    }
}
