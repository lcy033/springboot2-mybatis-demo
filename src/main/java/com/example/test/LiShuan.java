package com.example.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by finup on 2019/9/4.
 */
public class LiShuan {

    public static String test(TreeNode treeNodes) {
        int l = 0;
        int r = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        if (treeNodes != null) {
            queue.add(treeNodes);
        }

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode.getLeft() != null) {
                l = l + 1;
                queue.add(treeNode.getLeft());
            }

            if (treeNode.getRight() != null) {
                r = r + 1;
                queue.add(treeNode.getRight());
            }

        }

        if (l > r + 1 || r > l + 1) {
            return "不是" + l + ":" + r;
        }

        return "是" + l + ":" + r;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        treeNode.setLeft(treeNode1);
        treeNode.setRight(treeNode2);
        System.out.println(LiShuan.test(treeNode));
        System.out.printf("浮点型变量的的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                "is %s", 1.22f, 33, "stringVar");
        String fs = String.format("浮点型变量的的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                "is %s", 1.22f, 33, "stringVar");
        System.out.println(fs);
    }

}
