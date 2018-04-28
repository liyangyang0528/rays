package com.lyyco.rays.service.algorithm.week5;

import edu.princeton.cs.algs4.*;

/**
 * Author liyangyang
 * 2018/4/28
 */
public class KdTree {
    private static final RectHV CANVAS = new RectHV(0, 0, 1, 1);
    private Node root;
    private int count;

    public KdTree() {
        this.root = null;
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;

    }

    public void insert(Point2D p) {
        if (null == p) throw new NullPointerException("Point is null");
        root = insert(p, root, true, CANVAS);

    }

    public boolean contains(Point2D p) {
        if (null == p) throw new NullPointerException("Point is null");
        return get(p, root) != null;
    }

    public void draw() {
        draw(root);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) throw new NullPointerException("rect is null");
        SET<Point2D> set = new SET<>();
        range(set, rect, root);
        return set;
    }

    public Point2D nearest(Point2D p) {
        if (null == p) throw new NullPointerException("Point is null");
        if (null == root) return null;
        Point2D retP = null;
        double min = Double.MAX_VALUE;
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            double dis = p.distanceSquaredTo(x.p);

            if (dis < min) {
                retP = x.p;
                min = dis;
            }
            if (null != x.left && x.left.rect.distanceSquaredTo(p) < min) {
                queue.enqueue(x.left);
            }
            if (null != x.left && x.right.rect.distanceSquaredTo(p) < min) {
                queue.enqueue(x.right);
            }
        }
        return retP;
    }


    private Object get(Point2D p, Node n) {
        if (null == n) {
            return null;
        }
        // p和n节点中点相同
        if (n.p.equals(p)) {
            return n;
        }
        // p在n节点的左边或下边
        if (n.compareTo(p) > 0) {
            return get(p, n.left);
        } else {
            return get(p, n.right);
        }
    }

    private void draw(Node n) {
        if (null == n) return;
        draw(n.left);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        n.p.draw();
        StdDraw.setPenRadius();
        if (n.coordinate) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
        }
        draw(n.right);
    }

    private Node insert(Point2D p, Node n, boolean coordinate, RectHV rect) {
        if (null == n) {
            count++;
            return new Node(p, coordinate, rect);
        }
        if (p.equals(n.p)) return n;
        if (n.compareTo(p) > 0) {
            n.left = insert(p, n.left, !coordinate, childRect(n, true));
        } else {
            n.right = insert(p, n.right, !coordinate, childRect(n, false));
        }

        return n;
    }

    private RectHV childRect(Node n, boolean left) {
        RectHV rect;
        RectHV temp = n.rect;
        // 左子树
        if (left) {
            if (null != n.left) {
                return n.left.rect;
            }
            // x-coordinate 划分，生成左半边矩阵
            // y-coordinate 划分，生成下半边矩阵
            if (n.coordinate) {
                rect = new RectHV(temp.xmin(), temp.ymin(), n.p.x(), temp.ymax());
            } else {
                rect = new RectHV(temp.xmin(), temp.ymin(), temp.xmax(), n.p.y());
            }
        } else {
            if (null != n.right) {
                return n.right.rect;
            }
            if (n.coordinate) {
                rect = new RectHV(n.p.x(), temp.ymin(), temp.xmax(), temp.ymax());
            } else {
                rect = new RectHV(temp.xmin(), n.p.y(), temp.xmax(), temp.ymax());
            }
        }
        return rect;
    }
    private void range(SET<Point2D> set, RectHV rect, Node n) {
        if (null == n || !n.rect.intersects(rect)) return;
        // 矩形块 在节点n左边或下边 有一部分
        boolean left = (n.coordinate && rect.xmin() < n.p.x()) || (!n.coordinate && rect.ymin() < n.p.y());
        // 矩形块 在节点n右边或上边 有一部分
        boolean right = (n.coordinate && rect.xmax() >= n.p.x() || (!n.coordinate && rect.ymax() >= n.p.y()));
        if (left) {
            range(set, rect, n.left);
        }
        if (rect.contains(n.p)) {
            set.add(n.p);
        }
        if (right) {
            range(set, rect, n.right);
        }
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node left;
        private Node right;

        private boolean coordinate;

        public Node(Point2D p, boolean coordinate, RectHV rect) {
            this.p = p;
            this.coordinate = coordinate;
            this.rect = rect;
        }

        public int compareTo(Point2D that) {
            if (coordinate) {
                if (this.p.x() < that.x()) return -1;
                if (this.p.x() > that.x()) return -1;

            } else {
                if (this.p.y() < that.y()) return -1;
                if (this.p.y() > that.y()) return 1;
            }
            return 0;
        }
    }

    public static void main(String... args) {

    }
}
