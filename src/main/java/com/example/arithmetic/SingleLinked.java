package com.example.arithmetic;

import org.apache.commons.lang3.StringUtils;

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

    public boolean del(String name){
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

    public void pri(SingleLinkedNode node){
        System.out.println(node.name + "-" + node.idNo);
        if (node.next != null){
            this.pri(node.next);
        }
    }

    public static void main(String[] args) {
        SingleLinked singleLinked = new SingleLinked();
        singleLinked.add("1", "11");
        singleLinked.add("2", "22");
        singleLinked.add("3", "33");
        singleLinked.pri(singleLinked.head);
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
    }
}
