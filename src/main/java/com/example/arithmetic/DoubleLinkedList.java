package com.example.arithmetic;

/**
 * 双向链表
 *
 * @author : LCY
 * @date : create in 2021/3/16 2:45 下午
 */
public class DoubleLinkedList {

    private DoubleNode head;

    /* *
     * 表头插入节点
     */
    public void addFirst(int data) {
        // 创建新节点
        DoubleNode newNode = new DoubleNode();
        // 为新节点添加数据
        newNode.data = data;
        // 如果表头为空直接将新节点作为头
        if (head == null) {
            head = newNode;
        } else {
            // 将新节点的前驱节点指向null(声明的时候本来就是null)
            //新节点的后续节点指向表头
            newNode.next = head;
            // 将表头的前驱节点指向新节点
            head.previous = newNode;
            // head重新赋值
            head = newNode;
        }
    }

    /* *
     * 表尾插入节点
     */
    public void addLast(int data) {
        // 创建新节点
        DoubleNode newNode = new DoubleNode();
        // 为新节点添加数据
        newNode.data = data;
        // 如果表头为空直接将新节点作为头
        if (head == null) {
            head = newNode;
        } else {
            DoubleNode currentNode = head;
            //寻找尾节点
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            //表尾的后续节点指向新节点
            currentNode.next = newNode;
            //新节点的前驱节点指向表尾
            newNode.previous = currentNode;
        }
    }

    public void pri(DoubleNode currentNode) {
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList doubleList = new DoubleLinkedList();
        for (int i = 0; i < 5; i++) {
            doubleList.addLast(i);
        }
        doubleList.pri(doubleList.head);
        System.out.println("---------");
        for (int i = 6; i < 10; i++) {
            doubleList.addFirst(i);
        }
        doubleList.pri(doubleList.head);
    }


    /**
     * 双向链表节点
     */
    public class DoubleNode {
        //当前节点数据
        private Integer data;
        //下节点
        private DoubleNode next;
        //上节点
        private DoubleNode previous;
    }
}
