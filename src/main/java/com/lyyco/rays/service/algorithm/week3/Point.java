package com.lyyco.rays.service.algorithm.week3;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Create an immutable data type Point
 * that represents a point in the plane
 * Author liyangyang
 * 2018/4/9
 */
public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString() {

        return "(" + x + "," + y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    @Override
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y == that.y) {
            if (this.x < that.x) return -1;
            if (this.x == that.x) return 0;
        }
        return 1;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x) {
            if (this.y == that.y) return Double.NEGATIVE_INFINITY;
            else
                return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y) return 0;

        return (double) (this.y - that.y) / (this.x - that.x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {

        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            double d1 = slopeTo(o1);
            double d2 = slopeTo(o2);
            if (d1 < d2) return -1;
            if (d1 == d2) return 0;

            return 1;
        }
    }
}
