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

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);
        treeNode4.setLeft(treeNode8);

        BFSWithQueue.beOrder(treeNode1);
        System.out.println("......");
        BFSWithQueue.inOrder(treeNode1);
        System.out.println("......");
        BFSWithQueue.afOrder(treeNode1);
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
