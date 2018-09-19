package com.lyyco.rays.service.leetcode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Author liyangyang
 * 2018/8/16
 */
public class TreeSolution {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //654. Maximum Binary Tree
    //The size of the given array will be in the range [1,1000]
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int l, int r) {
        if (l == r)
            return null;
        int max_i = max(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = construct(nums, 1, max_i);
        root.right = construct(nums, max_i + 1, r);
        return root;
    }

    private int max(int[] nums, int l, int r) {
        int max_i = 1;
        for (int i = 1; i < r; i++) {
            if (nums[max_i] < nums[i]) {
                max_i = i;
            }
        }
        return max_i;
    }

    //814. Binary Tree Pruning ---mine answer--
    public TreeNode pruneTree(TreeNode root) {
        isZeroNode(root);
        return null;
    }

    private boolean isZeroNode(TreeNode root) {
        if (null == root) return true;
        if (0 == root.val && isZeroNode(root.left) && isZeroNode(root.right)) {
            root = null;
            return true;
        }
//        if(1 == root.val && isZeroNode(root.left))
        return false;
    }

    //814 Binary Tree Pruning
    public TreeNode pruneTreeOfficial(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    private boolean containsOne(TreeNode node) {
        if (node == null) return false;
        boolean a1 = containsOne(node.left);
        boolean a2 = containsOne(node.right);
        if (!a1) node.left = null;
        if (!a2) node.right = null;
        return node.val == 1 || a1 || a2;
    }

    //617. Merge Two Binary Trees ----mine solution
    //// Method 1: Recursive Solution
    // Time: O(n)
    // Space: O(height)
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return merge(t1, t2);
    }

    private TreeNode merge(TreeNode t1, TreeNode t2) {
        if (null == t1 && null == t2) {
            return null;
        }
        if (null == t1) return t2;
        if (null == t2) return t1;
        t1.val = t1.val + t2.val;
        t1.left = merge(t1.left, t2.left);
        t1.right = merge(t1.right, t2.right);
        return t1;
    }

    //617. Merge Two Binary Trees
    // Method 2: Iterative DFS
    // Time: O(n)
    // Space: O(height)
    public TreeNode mergeTreesDiscuss1(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        //TODO Use stack to help DFS ---其实就是栈，LIFO 深度优先遍历
        Deque<TreeNode[]> stack = new LinkedList<>();
        stack.offerLast(new TreeNode[]{t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pollLast();
            // no need to merge t2 into t1
            if (cur[1] == null) {
                continue;
            }
            // merge t1 and t2
            cur[0].val += cur[1].val;
            // if node in t1 == null, use node in t2 instead
            // else put both nodes in stack to merge
            if (cur[0].left == null) {
                cur[0].left = cur[1].left;
            } else {
                stack.offerLast(new TreeNode[]{cur[0].left, cur[1].left});
            }
            if (cur[0].right == null) {
                cur[0].right = cur[1].right;
            } else {
                stack.offerLast(new TreeNode[]{cur[0].right, cur[1].right});
            }
        }
        return t1;
    }

    //617. Merge Two Binary Trees
    // Method 3: Iterative BFS
    // Time: O(n)
    // Space: O(n)
    public TreeNode mergeTreesDiscuss2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        //TODO Use stack to help DFS  队列FIFO 广度优先
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{t1, t2});
        while (!queue.isEmpty()) {
            TreeNode[] cur = queue.poll();
            //t2==null no need to merge t2 into t1
            if (cur[1] == null) {
                continue;
            }
            // merge t1 and t2
            cur[0].val += cur[1].val;
            // if node in t1 == null, use node in t2 instead
            // else put both nodes in stack to merge
            if (cur[0].left == null) {
                cur[0].left = cur[1].left;
            } else {
                queue.offer(new TreeNode[]{cur[0].left, cur[1].left});
            }
            if (cur[0].right == null) {
                cur[0].right = cur[1].right;
            } else {
                queue.offer(new TreeNode[]{cur[0].right, cur[1].right});
            }
        }
        return t1;
    }

    //108. Convert Sorted Array to Binary Search Tree
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(0, nums.length - 1, nums);
    }

    private TreeNode build(int low, int high, int[] nums) {
        if (low > high) return null;
        int midum = (low + high) / 2;
        TreeNode root = new TreeNode(nums[midum]);
        root.left = build(low, midum - 1, nums);
        root.right = build(midum + 1, high, nums);
        return root;
    }

    //872. Leaf-Similar Trees
    //Two binary trees are considered leaf-similar if their leaf value sequence is the same.
    public boolean leafSimilarOffical(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    private void dfs(TreeNode node, List<Integer> leafValues) {
        if (node != null) {
            if (node.left == null && node.right == null)
                leafValues.add(node.val);
            dfs(node.left, leafValues);
            dfs(node.right, leafValues);
        }

    }

    //872. Leaf-Similar Trees discussion
    //TODO using only one Queue and dfs  2ms
    Deque<Integer> qu = new ArrayDeque<>();

    public boolean leafSimilarDiscussion1(TreeNode root1, TreeNode root2) {
        dfs(root1);
        return check(root2);
    }

    private void dfs(TreeNode cur) {
        if (cur.left == null && cur.right == null) {
            qu.offer(cur.val);
            return;
        }
        if (cur.left != null) {
            dfs(cur.left);
        }
        if (cur.right != null) {
            dfs(cur.right);
        }
    }

    private boolean check(TreeNode cur) {
        if (cur.left == null && cur.right == null) {
            return qu.poll() == cur.val;
        }
        boolean result = true;
        if (cur.left != null) {
            result = check(cur.left);
        }
        if (result && cur.right != null) {
            result = check(cur.right);
        }
        return result;
    }

    //Java without queue and array! Only calculating hash
    public boolean leafSimilarDiscussion2(TreeNode root1, TreeNode root2) {
        int[] f = new int[]{1};
        int[] s = new int[]{1};
        dfs(root1, f);
        dfs(root2, s);
        return f[0] == s[0];
    }

    private void dfs(TreeNode root, int[] h) {
        if (root.left == null && root.right == null) {
            h[0] = h[0] * 31 + root.val;
        }
        if (root.left != null) dfs(root.left, h);
        if (root.right != null) dfs(root.right, h);
    }

    //559. Maximum Depth of N-ary Tree
    //N-ary tree指一个节点下最多有n个子节点
    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /*
    Mine：我的想法是深度遍历树，每次遍历计数器+1，
    找到最深的叶子节点即为答案；
    但是发现计数器会加上其他非最深路径的长度，
    另外最深路径如何确定呢？
     */
    public int maxDepthDFS(Node root) {
        int depth = 0;
        Stack<Node> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.pop();
            depth++;
            for (Node child : node.children) {
                nodes.push(child);
            }
        }
        return 1;
    }

    /*
    试一下广度优先
    依旧是卡在层级如何表示上
    看了discuss知道了一种方法标识层级：广度优先展开root的children加入到队列
    这一层级可以用当前展开节点==last节点==root表示 第一层
    root展开后将last节点移动到队列最末尾的元素，代表第二层最后被遍历到的节点，即为第二层
     */
    public int maxDepthBFS(Node root) {
        int depth = 0;
        if (null == root) return depth;
        Queue<Node> nodes = new ArrayDeque<>(5000);
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            for (Node node1 : node.children) {
                nodes.offer(node1);
            }
        }
        return depth;
    }

    //TODO 递归代码未读懂 Java DFS 4-line solution
    public int maxDepthDiscuss1(Node root) {
        if (root == null) return 0;
        int max = 0;
        if (null != root.children) {
            for (Node child : root.children) {
                int current = maxDepthDiscuss1(child);
                max = Math.max(max, current);
            }
        }
        return max + 1;
    }

    /*
DFS:
Recursive:
Traverse the tree and return maximum depth at each subtree, then return max depth among all child subtrees.

Iterative:
Haven't done this way. Might need to store nodes with corresponding depth in a stack?

BFS:
Iterative:
Use a FIFO queue to traverse tree at level-order and increment depth when starting a new level. return the depth after traversal.
     */
    public int maxDepthDiscuss2(Node root) {
        if (root == null) return 0;
        return bfs(root);
        // return dfs(root, 1);
    }

    private int dfs(Node root, int depth) {
        if (root == null) return depth - 1;
        int max = depth, curr = depth;
        for (Node child : root.children) {
            curr = dfs(child, depth + 1);
            max = curr > max ? curr : max;
        }
        return max;
    }

    private int bfs(Node root) {
        if (root == null) return 0;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offerLast(root);
        int depth = 1;
        Node last = root;

        while (!queue.isEmpty()) {
            Node n = queue.pollFirst();
            List<Node> children = n.children;
            Node newLast = null;
            if (null != children && children.size() > 0) {
                for (int i = 0; i < children.size() - 1; i++) {
                    queue.offer(children.get(i));
                }
                newLast = children.get(children.size() - 1);
                queue.offer(newLast);
            }
//            for(Node child : n.children){
//                queue.offerLast(child);
//            }
            if (last == n) {
                if (queue.isEmpty()) {
                    return depth;
                } else {
                    last = newLast;
//                    last = queue.peekLast();
                    depth++;
                }
            }

        }
        return depth;
    }

    public int maxDepthDiscuss3(Node root) {
        int d = 0, cnt = 0;
        Queue<Node> q = new LinkedList<>();

        if (root == null) return d;

        q.add(root);

        while (!q.isEmpty()) {
            d++;
            cnt = q.size();
            for (int i = 0; i < cnt; i++) {
                Node n = q.poll();
                for (Node nn : n.children) {
                    q.add(nn);
                }
            }
        }

        return d;
    }

    //Basic Recursive Java solution w/o helper methods
    public int maxDepthDiscuss4(Node root) {
        if (root == null) return 0;
        if (null == root.children || root.children.size() == 0) return 1;
        int max = -1;
        int temp;
        for (int i = 0; i < root.children.size(); i++) {
            temp = 1 + maxDepthDiscuss4(root.children.get(i));
            if (temp > max) max = temp;
        }
        return max;
    }

    //894. All Possible Full Binary Trees
    /*
A full binary tree is a binary tree where
each node has exactly 0 or 2 children.
Return a list of all possible full binary trees with N nodes.
Each element of the answer is the root node of one possible tree.
Each node of each tree in the answer must have node.val = 0.
You may return the final list of trees in any order.
     */
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> ret = new ArrayList<>();
        if (n == 1) {
            // Exactly.
            ret.add(new TreeNode(0));
            return ret;
        } else {
                /*
                 * For a given number n, exclude the root node, there's n-1 nodes left.
                 *
                 * Because it's a FBT, so the son nodes is FBT too, and it must contains
                 * odd number of nodes.
                 *
                 * That is, root.left = allPossibleFBT(1) and root.right = allPossibleFBT(n - 2),
                 * or root.left = allPossibleFBT(3) and root.right = allPossibleFBT(n - 4),
                 * ...
                 * or root.left = allPossibleFBT(n - 2) and root.right = allPossibleFBT(1).
                 *
                 * Combining all the possible situation, there's the final result.
                 */
            for (int i = 1; i <= (n - 1) / 2; i += 2) {
                List<TreeNode> left = allPossibleFBT(i);
                List<TreeNode> right = allPossibleFBT(n - 1 - i);
                for (TreeNode leftNode : left) {
                    for (TreeNode rightNode : right) {
                        TreeNode node = new TreeNode(0);
                        node.left = leftNode;
                        node.right = rightNode;
                        ret.add(node);
                        if (i != (n - 1) / 2) {
                            TreeNode node1 = new TreeNode(0);
                            node1.left = rightNode;
                            node1.right = leftNode;
                            ret.add(node1);
                        }
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 701.Insert into a Binary Search Tree
     * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
     * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (null == root) return new TreeNode(val);
        //分治思想
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        else root.right = insertIntoBST(root.right, val);
        return root;
    }
    //700. Search in a Binary Search Tree
    //Given the root node of a binary search tree (BST) and a value.
    // You need to find the node in the BST that
    // the node's value equals the given value.
    // Return the subtree rooted with that node.
    // If such node doesn't exist, you should return NULL.
    public TreeNode searchBST(TreeNode root, int val) {
        return null;
    }
    public static void main(String... args) {
        TreeSolution treeSolution = new TreeSolution();
        Node root = new Node();
        root.val = 1;
        Node three = new Node();
        three.val = 3;
        Node five = new Node();
        five.val = 5;
        Node six = new Node();
        six.val = 6;
        List<Node> threesChildren = Lists.newArrayList(five, six);
        three.children = threesChildren;
        Node two = new Node();
        two.val = 2;
        Node four = new Node();
        four.val = 4;
        List<Node> children = Lists.newArrayList(three, two, four);
        root.children = children;
        treeSolution.maxDepthDiscuss4(root);
    }
}
