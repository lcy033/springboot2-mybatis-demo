package com.example.test;

import java.util.*;

/**
 * Created by finup on 2019/9/2.
 */
public class BFSWithQueue {


    /**
     * 使用Queue实现BFS 层级遍历二叉树
     */
    private static void BFSWithQueue1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        System.out.println(root.getVal());
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            //在这里处理遍历到的TreeNode节点
            if (treeNode.left != null) {
                queue.add(treeNode.left);
                System.out.print(treeNode.left.getVal() + " ");
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
                System.out.println(treeNode.right.getVal() + " ");
            }
        }
    }

    /**
     * 反转二叉
     *
     * @param root
     */
    private static void BFSWithQueue2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode temp = null;
        if (root != null) {
            queue.add(root);
        }
        System.out.println(root.getVal());
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;
            if (treeNode.left != null) {
                queue.add(treeNode.left);
                System.out.print(treeNode.left.getVal() + " ");
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
                System.out.println(treeNode.right.getVal() + " ");
            }
        }
    }

    /**
     * 前序
     */
    static void beOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val + " ");
        beOrder(root.left);
        beOrder(root.right);
    }

    /**
     * 中序
     */
    static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val + " ");
        inOrder(root.right);
    }

    /**
     * 后续
     */
    static void afOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        afOrder(root.left);
        afOrder(root.right);
        System.out.println(root.val + " ");
    }

    /**
     * 层级
     */
    static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val + " ");
    }

    /**
     * 二叉排序树、二叉查找树（查询）
     */
    static TreeNode find(TreeNode root, int data) {
        TreeNode p = root;
        while (p != null) {
            if (data < p.val) {
                p = p.left;
            } else if (data > p.val) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * 二叉查找树 插入
     */
    static void insert(TreeNode tree, int data) {
        if (tree == null) {
            tree = new TreeNode(data);
            return;
        }
        TreeNode p = tree;
        while (true) {
            if (data > p.val) {
                if (p.right == null) {
                    p.right = new TreeNode(data);
                    return;
                }
                p = p.right;
            } else { // data < p.data =
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 二叉查找树 删除
     */
    static void delete(TreeNode tree, int data) {
        TreeNode p = tree; // p 指向要删除的节点，初始化指向根节点
        TreeNode pp = null; // pp 记录的是 p 的父节点
        while (p != null && p.val != data) {
            pp = p;
            if (data > p.val) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) {
            return; // 没有找到
        }

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            TreeNode minP = p.right;
            TreeNode minPP = p; // minPP 表示 minP 的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.val = minP.val; // 将 minP 的数据替换到 p 中
            p = minP; // 下面就变成了删除 minP 了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        TreeNode child; // p 的子节点
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        if (pp == null) {
            tree = child; // 删除的是根节点
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);

        treeNode5.setLeft(treeNode4);
        treeNode5.setRight(treeNode6);
        treeNode4.setLeft(treeNode3);
        treeNode3.setLeft(treeNode2);
        treeNode2.setLeft(treeNode1);
        treeNode6.setRight(treeNode7);
        treeNode7.setRight(treeNode8);

        BFSWithQueue.delete(treeNode5, 7);

        BFSWithQueue.beOrder(treeNode5);
        System.out.println("......");
        BFSWithQueue.inOrder(treeNode5);
        System.out.println("......");
        BFSWithQueue.afOrder(treeNode5);
        System.out.println("......");

        BFSWithQueue.BFSWithQueue1(treeNode1);
//        BFSWithQueue.BFSWithQueue2(treeNode1);
//
//        Vector vector = new Vector();
//        vector.add("ddd");
//
//        List list = Collections.synchronizedList(new ArrayList<>());
//        Map map = Collections.synchronizedMap(new HashMap<>());
//
//        List l = new CopyOnWriteArrayList();
//        Set s = new CopyOnWriteArraySet();
    }

}
