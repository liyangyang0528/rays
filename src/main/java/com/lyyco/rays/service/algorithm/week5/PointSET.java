package com.lyyco.rays.service.algorithm.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Set;
import java.util.TreeSet;

/**
 * Author liyangyang
 * 2018/4/28
 */
public class PointSET {

    private Set<Point2D> set;

    public PointSET() {
        set = new TreeSet<>();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {

        return set.size();
    }

    public void insert(Point2D p) {
        if (null == p) throw new NullPointerException("Point is null");
        set.add(p);
    }

    public boolean contains(Point2D p) {
        if (null == p) throw new NullPointerException("Point is null");
        return set.contains(p);
    }

    public void draw() {
        for (Point2D p : set) {
            StdDraw.point(p.x(), p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) throw new NullPointerException("Point is null");
        Queue<Point2D> queue = new Queue<>();
        for (Point2D p : set) {
            if (rect.contains(p)) {
                queue.enqueue(p);
            }
        }
        return queue;
    }

    public Point2D nearest(Point2D p) {
        if (null == p) throw new NullPointerException("Point is null!");
        double minDis = Double.MIN_VALUE;
        Point2D res = null;
        for(Point2D q : set){
            double dis = q.distanceSquaredTo(p);
            if(dis < minDis){
                minDis = dis;
                res = q;
            }
        }
        return res;
    }

    public static void main(String... args) {

    }

}
