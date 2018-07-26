package com.lyyco.rays.service.algorithm.search;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * the syntax highlighted version
 * 初版红黑树
 * Author liyangyang
 * 2018/7/26
 */
public class RedBlackLiteBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private int n;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;

        Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }


    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        root = insert(root, key, val);
        root.color = BLACK;
    }

    private Node insert(Node h, Key key, Value val) {
        if (h == null) {
            n++;
            return new Node(key, val, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = insert(h.left, key, val);
        else if (cmp > 0) h.right = insert(h.right, key, val);
        else h.val = val;
        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && !isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        return h;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.left.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.right.color = RED;
        return x;
    }

    //调用前：two children are red, node is black
    //调用后：two children are black, node is red
    private void flipColors(Node h) {
        assert !isRed(h) && isRed(h.left) && isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Key min() {
        return min(root);
    }

    private Key min(Node x) {
        Key key = null;
        while (x != null) {
            key = x.key;
            x = x.left;
        }
        return key;
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
        Key key = null;
        while (x != null) {
            key = x.key;
            x = x.right;
        }
        return key;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue) {
        if (x == null) return;
        keys(x.left, queue);
        queue.enqueue(x.key);
        keys(x.right, queue);
    }

    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!is23()) StdOut.println("Not a 2-3 tree");
        if (!isBalanced()) StdOut.println("Not balanced");
        return isBST() && is23() && isBalanced();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }
    private boolean isBST(Node x,Key min,Key max){
        if(x == null) return true;
        if(min != null && x.key.compareTo(min) <= 0) return false;
        if(max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left,min,x.key) && isBST(x.right,x.key,max);
    }
    private boolean is23(){
        return is23(root);
    }
    private boolean is23(Node x){
        if(x == null) return true;
        if(isRed(x.right))return false;
        if(x != root && isRed(x) && isRed(x.left))return false;
        return is23(x.left) && is23(x.right);
    }
    private boolean isBalanced(){
        int black = 0;
        Node x =root;
        while (x != null){
            if(!isRed(x))black++;
            x = x.left;
        }
        return isBalanced(root,black);
    }
    private boolean isBalanced(Node x,int black){
        if(x == null) return black == 0;
        if(!isRed(x))black--;
        return isBalanced(x.left,black) && isBalanced(x.right,black);
    }


    public static void main(String[] args) {

        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        RedBlackLiteBST<String, Integer> st = new RedBlackLiteBST<String, Integer>();
        for (int i = 0; i < keys.length; i++)
            st.put(keys[i], i);

        StdOut.println("size = " + st.size());
        StdOut.println("min  = " + st.min());
        StdOut.println("max  = " + st.max());
        StdOut.println();


        // print keys in order using allKeys()
        StdOut.println("Testing keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // insert N elements in order if one command-line argument supplied
        if (args.length == 0) return;
        int n = Integer.parseInt(args[0]);
        RedBlackLiteBST<Integer, Integer> st2 = new RedBlackLiteBST<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            st2.put(i, i);
            int h = st2.height();
            StdOut.println("i = " + i + ", height = " + h + ", size = " + st2.size());
        }


        StdOut.println("size = " + st2.size());
    }

}
