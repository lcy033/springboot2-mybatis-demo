package com.example.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by finup on 2019/9/2.
 */
public class BFSWithQueue {

    //使用Queue实现BFS
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

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        BFSWithQueue.BFSWithQueue1(treeNode1);
        BFSWithQueue.BFSWithQueue2(treeNode1);

        Vector vector = new Vector();
        vector.add("ddd");

        List list = Collections.synchronizedList(new ArrayList<>());
        Map map = Collections.synchronizedMap(new HashMap<>());

        List l = new CopyOnWriteArrayList();
        Set s = new CopyOnWriteArraySet();
    }

}
