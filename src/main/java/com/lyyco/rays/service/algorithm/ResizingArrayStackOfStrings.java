package com.lyyco.rays.service.algorithm;

/**
 * 扩容的数组实现栈
 * com.lyyco.rays.service.algorithm
 * @Author liyangyang
 * 2018/3/27
 */
public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings(int capacity) {
        s = new String[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        if(N == s.length)
            resize(2*s.length);
        s[N++] = item;
    }

    private void resize(int cap) {
        String[] copy = new String[cap];
        for(int i =0;i<N;i++)
            copy[i] = s[i];
        s=copy;
    }

    public String pop() {
        //decrement N; then use to index into array
        //Loitering:Holding a reference to an object when it is no longer needed.
//        return s[--N];

        //this version avoids "loitering"
        //garbage collector can reclaim memory only if no outstanding references
        String item = s[--N];
        s[N] = null;
        //halve size of array s[] when array is one-quarter full.
        if(N>0 && N == s.length/4)
            resize(s.length/2);
        return item;

    }
}
