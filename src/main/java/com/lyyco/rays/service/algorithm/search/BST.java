package com.lyyco.rays.service.algorithm.search;

/**
 * Author liyangyang
 * 2018/6/4
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;

        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    //return value associated with key in the subtree rooted at x;
    private Value get(Node x, Key key) {
        if (null == x) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    // Search for key. Update value if found; grow table if new.
    //Change key’s value to val if key in subtree rooted at x
    // Otherwise, add new node to subtree associating key with val
    private Node put(Node x, Key key, Value val) {
        if (null == x) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

}
