package com.lyyco.rays.service.algorithm;

/**
 * 数组实现的栈
 * com.lyyco.rays.service.algorithm
 * Defect:Stack overflows when N exceeds capacity
 * @Author liyangyang
 * 2018/3/27
 */
public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        //use to index into array;then increment N
        s[N++] = item;
    }

    public String pop() {
        //decrement N; then use to index into array
        //Loitering:Holding a reference to an object when it is no longer needed.
//        return s[--N];
        //this version avoids "loitering"
        //garbage collector can reclaim memory only if no outstanding references
        String item = s[--N];
        s[N] = null;
        return item;

    }
}
