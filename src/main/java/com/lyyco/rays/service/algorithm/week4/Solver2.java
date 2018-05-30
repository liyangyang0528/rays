package com.lyyco.rays.service.algorithm.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver2 {
    private boolean isSolve = false;  
    private int move = -1;  
  
    private class SearchNode implements Comparable<SearchNode>{  
        private final Board2 board;
        private final int move;  
        private final int priority;  
        private final SearchNode parent;  
        private final boolean isTwin;  
          
        public SearchNode(Board2 board, int move, SearchNode parent, boolean isTwin){
            this.board = board;  
            this.move = move;  
            this.priority = board.manhattan() + move;  
            this.parent = parent;  
            this.isTwin = isTwin;  
        }  
  
        @Override  
        public int compareTo(SearchNode that) {  
            if (this.board.equals(that.board)) return 0;  
            if (this.priority < that.priority) return -1;  
            else return 1;  
        }  
    }  
      
    private MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
        public int compare(SearchNode o1, SearchNode o2) {  
            if (o1.priority < o2.priority) return -1;  
           else if (o1.priority == o2.priority) return 0;  
           else return 1;  
        }  
    });  
      
    private Stack<Board2> solutionQueue = new Stack<Board2>();
      
    // find a solution to the initial board (using the A* algorithm)  
    public Solver2(Board2 initial){
        Board2 initialTwin = initial.twin();
        SearchNode initSearchNode = new SearchNode(initial, 0, null, false);  
        SearchNode initSearchNodeTwin = new SearchNode(initialTwin, 0, null, true);  
        minPQ.insert(initSearchNode);  
        minPQ.insert(initSearchNodeTwin);  
        solve();  
    }  
      
    private void solve(){  
        while(true){  
            //solve for original  
            SearchNode searchNode = minPQ.delMin();  
            if (searchNode.board.isGoal()){  
                if (searchNode.isTwin){  
                    this.isSolve = false;  
                    this.move = -1;  
                } else {  
                    this.isSolve = true;  
                    this.move = searchNode.move;  
                    this.solutionQueue.push(searchNode.board);  
                    while(searchNode.parent != null){  
                        searchNode = searchNode.parent;  
                        this.solutionQueue.push(searchNode.board);  
                    }  
                }  
                break;  
            }else{  
                for (Board2 neiborBoard: searchNode.board.neighbors()){
                    SearchNode neiborNode = new SearchNode(neiborBoard, searchNode.move+1, searchNode, searchNode.isTwin);  
                    if (searchNode.parent == null){  
                        minPQ.insert(neiborNode);  
                    } else if (!searchNode.parent.board.equals(neiborNode.board)){  
                        minPQ.insert(neiborNode);  
                    }  
                }  
            }  
        }  
    }  
      
    // is the initial board solvable?  
    public boolean isSolvable(){  
        return this.isSolve;  
    }  
      
    // min number of moves to solve initial board; -1 if no solution  
    public int moves(){  
        return this.move;  
    }  
      
    // sequence of boards in a shortest solution; null if no solution  
    public Iterable<Board2> solution(){
        if (this.isSolve){  
            return this.solutionQueue;  
        }else{  
            return null;  
        }  
          
    }  
      
    public static void main(String[] args) {  
        // create initial board from file  
        In in = new In(args[0]);
        int N = in.readInt();  
        int[][] blocks = new int[N][N];  
        for (int i = 0; i < N; i++)  
            for (int j = 0; j < N; j++)  
                blocks[i][j] = in.readInt();  
        Board2 initial = new Board2(blocks);
  
        // solve the puzzle  
        Solver2 solver = new Solver2(initial);
  
        // print solution to standard output  
        if (!solver.isSolvable())  
            StdOut.println("No solution possible");
        else {  
            StdOut.println("Minimum number of moves = " + solver.moves());  
            for (Board2 board : solver.solution())
                StdOut.println(board);  
        }  
    }  
}  