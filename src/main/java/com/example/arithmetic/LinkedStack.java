package com.example.arithmetic;

import java.util.LinkedList;

/**
 * 使用链表实现栈 线程不安全 栈是一种操作受限的数据结构，只支持入栈和出栈操作
 *
 * @author : LCY
 * @date : create in 2021/2/19 4:46 下午
 */
public class LinkedStack<E> {

    // 链表
    private final LinkedList<E> linkedList;
    // 栈大小
    private final int num;

    // 初始化
    LinkedStack(int num) {
        this.num = num;
        this.linkedList = new LinkedList<>();
    }

    // 入栈操作
    public boolean push(E e) {
        if (linkedList.size() == num){
            return false;
        }
        return linkedList.add(e);
    }

    // 出栈操作
    public E pop() {
        if (linkedList.size() == 0){
            return null;
        }
        E e = linkedList.getLast();
        linkedList.removeLast();
        return e;
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack(3);
        System.out.println(linkedStack.push("1"));
        System.out.println(linkedStack.push("2"));
        System.out.println(linkedStack.push("3"));
        System.out.println(linkedStack.push("4"));
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.push("3"));
        System.out.println(linkedStack.push("4"));
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
    }

}
