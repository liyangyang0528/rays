package com.lyyco.rays.service.algorithm;

/**
 * 改进
 * com.lyyco.rays.service.algorithm
 *
 * @Author liyangyang
 * 2018/3/26
 */
public class QuickUnionUFWeighted {
    private int[] id;
    private int[] sz;

    public QuickUnionUFWeighted(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i])
            /*
             Make every other node in path point to its
             grandparent (thereby halving path length)
             */
//            id[i] = id[id[i]];
            i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}
