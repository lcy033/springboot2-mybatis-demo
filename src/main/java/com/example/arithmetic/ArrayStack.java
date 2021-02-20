package com.example.arithmetic;

/**
 * 使用数组实现栈 线程不安全 栈是一种操作受限的数据结构，只支持入栈和出栈操作
 * @author : LCY
 * @date : create in 2021/2/19 4:46 下午
 */
public class ArrayStack {

    // 数组
    private String[] items;

    // 数据个数
    private int count;

    // 栈的大小
    private int num;

    // 初始化
    ArrayStack(int num){
        this.items = new String[num];
        this.count = 0;
        this.num = num;
    }

    // 入栈操作
    public boolean push(String item) {
        if (count == num){
            return false;
        }
        items[count] = item;
        ++count;
        return true;
    }

    // 出栈操作
    public String pop() {
        if (count == 0){
            return null;
        }
        --count;
        String item = items[count];
        this.items[this.count] = null;
        return item;
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        System.out.println(arrayStack.push("1"));
        System.out.println(arrayStack.push("2"));
        System.out.println(arrayStack.push("3"));
        System.out.println(arrayStack.push("4"));
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.push("3"));
        System.out.println(arrayStack.push("4"));
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }

}
