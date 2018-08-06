package com.lyyco.rays.service.algorithm.week5;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private int size = 0;
    private Node root = null;

    private class Node {
        private Point2D p;
        private Node left; //left child
        private Node right; //right child
        public Node(Point2D p) {
            this.p = p;
            this.left = null;
            this.right = null;
        }        
    }    
    public KdTree() {
        size = 0;
        root = null;        
    }    
    public int size() {
        return size;
    }    
    public boolean isEmpty()                        // is the set empty?
    {
       return size == 0;
    }
    private boolean isGoLeft(Point2D pointNode, Point2D p, int depth) 
    {
        return (depth % 2 == 0) ? (p.x() < pointNode.x()) : (p.y() < pointNode.y());
    }
    public void insert(Point2D p) {
        if (isEmpty()) {
            root = new Node(p);
            size++;
            return;
        }
        insert(root, p, 0);
    }
    private void insert(Node current, Point2D point, int depth) {
        if (current.p.equals(point)) return;
        boolean goLeft = isGoLeft(current.p, point, depth);
        if (goLeft) {
            if (current.left == null) {
                current.left = new Node(point);
                size++;
                return;
            } else {
                insert(current.left, point, depth + 1);                
            }            
        } else {
            if (current.right == null) {
                current.right = new Node(point);
                size++;
             } else {
                insert(current.right, point, depth + 1);
            }
        }
        
    }
    private boolean contains(Node current, Point2D p, int depth)              // does the set contain the point p?
    {
       if (current.p.equals(p))  return true;
       boolean goLeft = isGoLeft(current.p, p, depth);
       if (goLeft)
       {
          if (current.left == null)
          {
               return false;
          } else {
             return contains(current.left, p, depth + 1);
          }
       } else {
          if (current.right == null)
          {            
             return false;
          } else {
             return contains(current.right, p, depth + 1);
          }
       }
    }
     
    public boolean contains(Point2D p)              // does the set contain the point p?
    {
       if (root == null) 
       {
          return false;
       }         
       return contains(root, p, 0);
    }
    private void draw(Node current)
    {
       if (current == null) return;
       current.p.draw();       
       draw(current.left);
       draw(current.right);
       
    }
    public void draw()                              // draw all of the points to standard draw
    {
       draw(root);
    }
    private void range(Node current, RectHV rect, SET<Point2D> result, int depth)
    {
       if (current == null) return;
       if (rect.contains(current.p)) result.add(current.p);
       if (depth % 2 == 0)
       {
          if (current.p.x() < rect.xmin()) 
          {
             range(current.right, rect, result, depth + 1);
          } else if (current.p.x() > rect.xmax()) 
          {
             range(current.left, rect, result, depth + 1);
          } else
          {
             range(current.right, rect, result, depth + 1);
             range(current.left, rect, result, depth + 1);               
          }            
       } else {
          if (current.p.y() < rect.ymin()) 
          {
             range(current.right, rect, result, depth + 1);
          } else if (current.p.y() > rect.ymax()) 
          {
             range(current.left, rect, result, depth + 1);
          } else
          {
             range(current.right, rect, result, depth + 1);
             range(current.left, rect, result, depth + 1);               
          }            
       }         
    }
    public Iterable<Point2D> range(RectHV rect)     // all points in the set that are inside the rectangle
    {
       SET<Point2D> result = new SET<Point2D>();
       range(root, rect, result, 0);
       return result;
    }
    private class PointDist
    {
       private Point2D point;
       private double distSq;
       public PointDist(Point2D p, double distSq)
       {
          this.point = p;
          this.distSq = distSq;
       }
    }
    private PointDist nearest(Node current, Point2D p, int depth, PointDist previous, RectHV rect) 
    {
       PointDist result = previous;
       double distSq = current.p.distanceSquaredTo(p);
       if (distSq < previous.distSq) {
          result = new PointDist(current.p, distSq);
       }         
       double xmin = rect.xmin();
       double ymin = rect.ymin();
       double xmax = rect.xmax();
       double ymax = rect.ymax();
       RectHV leftRect, rightRect;
       if (depth % 2 == 0) {
           leftRect = new RectHV(xmin, ymin, current.p.x(), ymax);
           rightRect = new RectHV(current.p.x(), ymin, xmax, ymax);              
       } else {
           leftRect = new RectHV(xmin, ymin, xmax, current.p.y());
           rightRect = new RectHV(xmin, current.p.y(), xmax, ymax);              
       }
       
       boolean goLeft = isGoLeft(current.p, p, depth); //p in the left of current.p
       if (goLeft)
       {
          if (current.left != null) {
             result = nearest(current.left, p, depth + 1, result, leftRect);
          }
          double distanceSquaredToRect = rightRect.distanceSquaredTo(p);
          if ((current.right != null) && (distanceSquaredToRect < result.distSq)) {
              result = nearest(current.right, p, depth + 1, result, rightRect);
          }          
       } else {
          if (current.right != null) {
             result = nearest(current.right, p, depth + 1, result, rightRect);
          }
          double distanceSquaredToRect = leftRect.distanceSquaredTo(p);
          if ((current.left != null) && (distanceSquaredToRect < result.distSq)) {
              result = nearest(current.left, p, depth + 1, result, leftRect);
          }
       }       
       return result;
    }
    public Point2D nearest(Point2D p)               // a nearest neighbor in the set to p; null if set is empty
    {
       if (root == null) 
       {
          return null;
       }
       PointDist result = new PointDist(new Point2D(0, 0), Double.MAX_VALUE);
       RectHV rect = new RectHV(-Double.MAX_VALUE, -Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
       result = nearest(root, p, 0, result, rect);
       return result.point;
    }
}