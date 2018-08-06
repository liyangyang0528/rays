package com.lyyco.rays.service.algorithm.week5;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.Iterator;

public class PointSET {
    private SET<Point2D> set;
    public PointSET() {
        set = new SET<Point2D>();
        
    }
    public boolean isEmpty() {
        return set.size() == 0;
    }
    public int size() {
        return set.size();
    }
    public void insert(Point2D p) {
        if (!contains(p)) set.add(p);
    }
    public boolean contains(Point2D p) {
         return set.contains(p);
    }
    public void draw() {
        Iterator<Point2D> iter = set.iterator();
        while (iter.hasNext()) {
            iter.next().draw();
        }
    }
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> result = new SET<Point2D>();
        Iterator<Point2D> iter = set.iterator();
        while (iter.hasNext()) {
            Point2D p = iter.next();
            if (rect.contains(p)) {
                result.add(p);
            }
        }
       return result;
    }
    public Point2D nearest(Point2D p) {
        Iterator<Point2D> iter = set.iterator();
        double shortestdis = Double.MAX_VALUE;
        Point2D result = null;
        while (iter.hasNext()) {
            Point2D point = iter.next();
            double dis = point.distanceSquaredTo(p);
            if (dis < shortestdis) {
                shortestdis = dis;
                result = point;
            }
            
        }
        return result;
    }
    
}