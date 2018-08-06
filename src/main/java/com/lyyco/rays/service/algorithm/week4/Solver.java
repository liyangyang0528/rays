package com.lyyco.rays.service.algorithm.week4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import java.util.Iterator;

public class Solver {
    private Stack<Board> stack = new Stack<Board>();
    private boolean isSolvable = false;
    private class Node implements Comparable<Node> {
        private Board board;
        private int move;
        private int priority;
        private Node previous;
        
        public Node(Board board, int move, Node previous) {
            this.board = board;
            this.move = move;
            this.previous = previous;
            this.priority = move + board.manhattan();
        }
        public int compareTo(Node that) {
            if (this.priority > that.priority) return 1;
            if (this.priority < that.priority) return -1;
            return 0;
            
        }
    }
    public Solver(Board initial) {
        MinPQ<Node> pq = new MinPQ<Node>();
        Node node = new Node(initial, 0, null);
        pq.insert(node);
        while (!node.board.isGoal() && !node.board.twin().isGoal()) {
            node = pq.delMin();
            Iterator<Board> iter = node.board.neighbors().iterator();
            while (iter.hasNext()) {
                Board neighbor = iter.next();
                if ((node.previous == null) || (!node.previous.board.equals(neighbor))) {
                    pq.insert(new Node(neighbor, node.move +  1, node));
                }
            }
        }
        if (node.board.isGoal()) {
            isSolvable = true;
            stack = new Stack<Board>();
            while (node != null) {
                stack.push(node.board);
                node = node.previous;
            }
        } else {
            isSolvable = false;
        }
        
    }
     public boolean isSolvable() {
         return isSolvable;
     }
     public int moves() {
         return stack.size() - 1;
     }
     public Iterable<Board> solution() {
         if (!isSolvable) return null;
         Queue<Board> res = new Queue<Board>();
         Iterator<Board> iter = stack.iterator();
         while (iter.hasNext()) {
             res.enqueue(iter.next());
         }
         return res;
     }
       
}