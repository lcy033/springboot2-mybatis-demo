package com.example.arithmetic;

/**
 * 循环队列
 * @author : LCY
 * @date : create in 2021/3/4 6:35 下午
 */
public class CircularQueue {

    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;
    // head 表示队头下标，tail 表示队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为 capacity 的数组
    public CircularQueue(int capacity) {
        items = new String[capacity + 1];
        n = capacity + 1;
    }

    // 入队
    public boolean enqueue(String item) {
        // 队列满了 （公式）
        if ((tail + 1) % n == head){
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail){
            return null;
        }
        String ret = items[head];
        // 公式
        head = (head + 1) % n;
        return ret;
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(4);
        System.out.println(circularQueue.enqueue("1"));
        System.out.println(circularQueue.enqueue("2"));
        System.out.println(circularQueue.enqueue("3"));
        System.out.println(circularQueue.enqueue("4"));
        System.out.println(circularQueue.enqueue("5"));
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.enqueue("3"));
        System.out.println(circularQueue.enqueue("4"));
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
    }

}
