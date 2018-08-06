package com.lyyco.rays.service.algorithm.week4;

import edu.princeton.cs.algs4.Queue;

public class Board {
    private int N;
    private int[][] blocks;
    private int row0, column0;
    public Board(int[][] blocks) { //construct a board from an N-by-N array of blocks.
        N = blocks.length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    row0 = i;
                    column0 = j;
                    
                }
            }
        }
    }
    public int dimension() {
        return N;
    }
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                /*if ((i == N - 1) && (j == N - 1)) {
                    if (blocks[i][j] != 0) {
                        hamming++;
                    }*/
                
                if ((blocks[i][j] != 0) && (blocks[i][j]) != (i * N + j + 1)) {
                    hamming++;
                }
                
            }
        }
        return hamming;
    }
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((blocks[i][j] != 0) && (blocks[i][j]) != (i * N + j + 1)) {
                    int temp = blocks[i][j] - 1;
                    int row = temp / N;
                    int column = temp % N;
                    int diff1 = i - row;
                    if (diff1 < 0) diff1 = -diff1;
                    int diff2 = j - column;
                    if (diff2 < 0) diff2 = -diff2;
                    manhattan = manhattan + diff1 + diff2;
                }
            }
        }
        return manhattan;
    }
    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i == N - 1) && (j == N - 1)) {
                    if (blocks[i][j] != 0) {
                        return false;
                    }
                } else {
                    if ((blocks[i][j]) != (i * N + j + 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public Board twin() {
        int rowNum = (row0 +  1) % N;
        return exchangeTile(rowNum, 0, rowNum, 1);
        
    }
    private Board exchangeTile(int r1, int c1, int r2, int c2) {
        int[][] neighbor =  new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                neighbor[i][j] = blocks[i][j];
            }
        }
        int temp = neighbor[r1][c1];
        neighbor[r1][c1] = neighbor[r2][c2];
        neighbor[r2][c2] = temp;
        return new Board(neighbor);
    }
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (N != that.dimension()) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != that.blocks[i][j]) return false;
            }
        }
        return true;
    }
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<Board>();
        if (row0 >= 1) {
            neighbors.enqueue(exchangeTile(row0 - 1, column0, row0, column0));
        }
        if (row0 <= N - 2) {
            neighbors.enqueue(exchangeTile(row0 + 1, column0, row0, column0));
        }
        if (column0 >= 1)
           {
              neighbors.enqueue(exchangeTile(row0, column0 - 1, row0, column0));
           }
        if (column0 <= N - 2)
           {
              neighbors.enqueue(exchangeTile(row0, column0 + 1, row0, column0));
           }
            
        return neighbors;
        
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
              s.append("\n");
        }
        return s.toString();        
    }
}