package com.example.arithmetic;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 单链表
 *
 * @author : LCY
 * @date : create in 2021/3/5 10:48 上午
 */
public class SingleLinked {

    private final SingleLinkedNode head = new SingleLinkedNode("", "");

    public void add(String name, String idNo) {
        SingleLinkedNode single = new SingleLinkedNode(name, idNo);
        SingleLinkedNode node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = single;
    }

    public String get(String name) {
        SingleLinkedNode node = head;
        while (!StringUtils.equals(node.name, name)) {
            if (node.next == null) {
                return null;
            }
            node = node.next;
        }
        return node.idNo;
    }

    public boolean del(String name) {
        SingleLinkedNode node = head;
        while (true) {
            if (node.next != null && StringUtils.equals(node.next.name, name)) {
                node.next = node.next.next;
                return true;
            }
            if (node.next == null) {
                return false;
            }
            node = node.next;
        }
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public SingleLinkedNode reverseListNode(SingleLinkedNode head) {
        if (head.next == null) {
            return head;
        }
        // 上一个节点
        SingleLinkedNode preNode = null;
        // 当前节点
        SingleLinkedNode curNode = head;
        // 下一个节点
        SingleLinkedNode nextNode = null;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    /**
     * 求链表中间节点
     * @param head
     * @return
     */
    public SingleLinkedNode middleNode(SingleLinkedNode head) {
        // 快
        SingleLinkedNode fast = head;
        // 慢
        SingleLinkedNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public void pri(SingleLinkedNode node) {
        System.out.println(node.name + "-" + node.idNo);
        if (node.next != null) {
            this.pri(node.next);
        }
    }

    public static void main(String[] args) {
        SingleLinked singleLinked = new SingleLinked();
        singleLinked.add("1", "11");
        singleLinked.add("2", "22");
        singleLinked.add("3", "33");
        singleLinked.add("4", "44");
        System.out.println("---" + singleLinked.middleNode(singleLinked.head));
        singleLinked.pri(singleLinked.head);
        singleLinked.pri(singleLinked.reverseListNode(singleLinked.head));
        System.out.println(singleLinked.get("2"));
        System.out.println(singleLinked.del("2"));
        System.out.println(singleLinked.del("4"));
        singleLinked.pri(singleLinked.head);

    }


    static class SingleLinkedNode {

        private final String name;

        private final String idNo;

        private SingleLinkedNode next;

        SingleLinkedNode(String name, String idNo) {
            this.name = name;
            this.idNo = idNo;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("name", name)
                    .append("idNo", idNo)
                    .toString();
        }
    }
}
