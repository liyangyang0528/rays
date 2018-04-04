package com.lyyco.rays.service.algorithm;

/**
 * hashtable
 * Author liyangyang
 * 2018/4/4
 */
public class SeparateChainingHashST<Key, Value> {
    private int M = 97;
    private Node[] st = new Node[M];

    private static class Node {
        private Object key;
        private Object val;
        private Node next;
    }

    private int hash(Key key) {
        // it's an efficient way to clear out bits of register
        // ANDing (&) with this mask effectively sets the signed bit to 0,
        // which means the number will always be positive
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key))
                return (Value) x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
//        st[i] = new Node(key, val, st[i]);
    }
}
