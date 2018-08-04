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
     * 基于有序数组的二分查找
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
    //递归的二分查找
    public int rank(Key key,int lo,int hi){
        if(hi < lo) return lo;
        int mid = lo + (hi - lo)/2;
        int cmp = key.compareTo(keys[mid]);
        if(cmp < 0) return rank(key,lo,mid-1);
        else if(cmp > 0) return rank(key,mid+1,hi);
        else return mid;

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key min(){
        return keys[0];
    }
    public Key max(){
        return keys[N-1];
    }
    public Key select(int k){
        return keys[k];
    }
    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }
    public Key delete(Key key){
        //TODO
        return null;
    }
    public Key floor(Key key){
        //TODO
        return null;
    }
//    public Iterable<Key> keys(Key lo,Key hi){
//        Queue<Key> q = new Queue<Key>();
//        for(int i = rank(lo);i<rank(hi);i++){
//            q.enqueue(keys[i]);
//            if(contains(hi))
//                q.enqueue(keys[rank(hi)]);
//            return q;
//        }
//    }
    public static void main(String...args){

    }
}
