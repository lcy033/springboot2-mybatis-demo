package com.example.test;

import java.util.LinkedList;
import java.util.Queue;

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
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            //在这里处理遍历到的TreeNode节点
            System.out.print(treeNode.getVal());
            if (treeNode.left != null) {
                queue.add(treeNode.left);
                System.out.println(treeNode.getVal() + " ");
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
                System.out.println(treeNode.getVal() + " ");
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
    }

}
