package com.example.arithmetic;

/**
 * 数组实现队列 顺序队列
 *
 * @author : LCY
 * @date : create in 2021/2/20 3:17 下午
 */
public class ArrayQueue {

    // 数组：items
    private String[] items;
    // 数组大小：n
    private int n = 0;
    // head 表示队头下标
    private int head = 0;
    // tail 表示队尾下标
    private int tail = 0;

    // 申请一个大小为 capacity 的数组
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        // 如果 tail == n 表示队列已经满了
        if (tail == n) {
            return false;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        for (int i = 0; i < tail - 1; i++) {
            items[i] = items[i + 1];
        }
        --tail;
        items[tail] = null;
        return ret;
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        System.out.println(arrayQueue.enqueue("1"));
        System.out.println(arrayQueue.enqueue("2"));
        System.out.println(arrayQueue.enqueue("3"));
        System.out.println(arrayQueue.enqueue("4"));
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.enqueue("3"));
        System.out.println(arrayQueue.enqueue("4"));
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
    }

}
