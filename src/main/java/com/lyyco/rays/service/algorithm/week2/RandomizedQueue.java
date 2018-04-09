package com.lyyco.rays.service.algorithm.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Randomized queue
 * A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random
 * from items in the data structure
 * Author liyangyang
 * 2018/4/2
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public RandomizedQueue() {
        this.size = 0;
        this.items = (Item[]) new Object[1];
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return this.size;
    }                        // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (null == item) throw new NullPointerException("Can't enqueue null value");
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }           // add the item

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("");
        if (size == items.length / 4) {
            resize(items.length / 2);
        }
        int random = StdRandom.uniform(size);
        Item item = items[random];
        items[random] = items[--size];//TODO
        items[size] = null;
        return item;
    }                    // remove and return a random item

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty!");
        return items[StdRandom.uniform(size)];

    }                     // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }         // return an independent iterator over items in random order

    public static void main(String[] args) {
    }   // unit testing (optional)

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] random;
        private int current;
        public RandomizedQueueIterator(){
            this.random = new int[size];
            for(int i=0;i<size;i++){
                random[i] = i;
            }
            StdRandom.shuffle(random);
            current = 0;
        }
        @Override
        public boolean hasNext() {
            return current != random.length;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("");
            return items[random[current++]];
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("Remove operator is unsupported!");
        }
    }
}
