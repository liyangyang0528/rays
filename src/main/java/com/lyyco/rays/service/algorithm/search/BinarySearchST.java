package com.lyyco.rays.service.algorithm.search;

/**
 * Author liyangyang
 * 2018/6/4
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];

    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }
    //search for key. update value if found;grow table if new.
    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;

    }

    /**
     *
     The reason that we keep keys in an ordered array is so that we can
     use array indexing to dramatically reduce the number of compares required for each
     search, using the classic and venerable binary search algorithm
     * @param key
     * @return
     */
    public int rank(Key key) {
        int lo = 0,hi = N-1;
        while (lo<=hi){
            int mid = lo + (hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) hi = mid-1;
            else if(cmp > 0) lo = mid+1;
            else return mid;
        }
        return lo;
    }

    public boolean isEmpty() {
        return N == 0;
    }
}
