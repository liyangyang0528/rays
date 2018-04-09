package com.lyyco.rays.service.algorithm.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * examines 4 points at a time and checks
 * whether they all lie on the same line segment,
 * returning all such line segments.
 * To check whether the 4 points p, q, r, and s are collinear,
 * check whether the three slopes between p and q, between p and r,
 * and between p and s are all equal.
 * Author liyangyang
 * 2018/4/9
 */
public class BruteCollinearPoints {

    private LineSegment[] ls;

    public BruteCollinearPoints(Point[] points) {
        //检查Point[]数组
        if (points == null) throw new NullPointerException("argument is null");
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new NullPointerException("array contains null point");
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("array contains a repeated point");
                }
            }
        }
        Point[] ps = points.clone();
        Arrays.sort(ps);
        List<LineSegment> list = new ArrayList<>();
        //brute force
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point[] p = new Point[4];
                        p[0] = ps[i];
                        p[1] = ps[j];
                        p[2] = ps[k];
                        p[3] = ps[l];
                        double s1 = p[0].slopeTo(p[1]);
                        double s2 = p[0].slopeTo(p[2]);
                        if (s1 != s2) continue;
                        double s3 = p[0].slopeTo(p[3]);
                        if (s1 == s3) {
                            Arrays.sort(p);
                            list.add(new LineSegment(p[0], p[3]));
                        }
                    }
                }
            }
        }
        // transform to array
        ls = list.toArray(new LineSegment[list.size()]);

    }

    // the number of line segments
    public int numberOfSegments() {

        return ls.length;
    }

    // the line segments
    public LineSegment[] segments() {

        return ls.clone();
    }
}
