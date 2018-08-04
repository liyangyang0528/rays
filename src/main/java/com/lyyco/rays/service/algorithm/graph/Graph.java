package com.lyyco.rays.service.algorithm.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 无向图
 * Author liyangyang
 * 2018/8/2
 */
public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;//邻接表

    Graph(int V){
        this.V=V;
        this.E=0;
        adj = (Bag<Integer>[])new Bag[V];
        for(int v = 0;v<V;v++){
            adj[v] = new Bag<>();
        }
    }
    Graph(In in){
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0;i<E;i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }
    int V(){return V;}//顶点数
    int E(){return E;}//边数
    void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }//添加一条边v-w
    Iterable<Integer> adj(int v){
        return adj[v];
    }//和V相邻的所有顶点
    public String toString(){
        return null;
    }//对象的字符串表示
}
