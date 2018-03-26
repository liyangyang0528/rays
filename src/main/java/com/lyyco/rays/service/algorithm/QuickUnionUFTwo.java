package com.lyyco.rays.service.algorithm;

/**
 * com.lyyco.rays.service.algorithm
 *
 * @Author liyangyang
 * 2018/3/26
 */
public class QuickUnionUFTwo {
    private int[] id;

    public QuickUnionUFTwo(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i])
            i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
