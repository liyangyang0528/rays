package com.lyyco.rays.service.algorithm.week3;

/**
 * Line segment data type. To represent line segments in the plane
 * Author liyangyang
 * 2018/4/9
 */
public class LineSegment {

    private final Point p;
    private final Point q;

    public LineSegment(Point p,Point q){
        if(p == null || q == null){
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.q = q;

    }
    // draws this line segment
    public void draw(){
        p.drawTo(q);
    }
    // string representation
    public String toString(){

        return p + "->" + q;
    }
}
