package com.lyyco.rays.service.algorithm.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 图处理算法
 * Author liyangyang
 * 2018/8/2
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    private void dfs(Graph g, int s) {
        marked[s]=true;
        count++;
        for(int w:g.adj(s))
        if(!marked(w))
            dfs(g,s);
    }
    public boolean marked(int w){
        return marked[w];
    }
    public int count(){
        return count;
    }

    public static void main(String...args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G,s);
        for(int v = 0; v<G.V();v++){
            if(search.marked(v))
                StdOut.println(v+"");
        }
        StdOut.println();
        if(search.count() != G.V())
            StdOut.println("Not connected");
        else StdOut.println("Connected");
    }
}
