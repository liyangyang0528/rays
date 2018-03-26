package com.lyyco.rays.service.algorithm;

/**
 * 1.2Quick find
 * com.lyyco.rays.service.algorithm
 *
 * @Author liyangyang
 * 2018/3/26
 */
public class QuickUnionUFOne {
    private int[] id;

    public QuickUnionUFOne(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }


    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid)
                id[i] = qid;
        }
    }
}
