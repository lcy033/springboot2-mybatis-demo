package com.example.test.lru;

import java.util.HashMap;

/**
 * 设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put
 */
public class LruCache {
    //方便通过key快速定位结点
    private HashMap<Integer, LinkedNode> cache = new HashMap();
    //当前数量
    private int size;
    //缓存容量
    private int capacity;
    //头节点
    private LinkedNode head;
    //尾节点
    private LinkedNode tail;

    class LinkedNode {
        int key;
        int value;
        LinkedNode pre;
        LinkedNode next;
    }

    /**
     * 初始化
     * @param capacity 容量
     */
    public LruCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 移除节点
     *
     * @param node
     */
    private void removeNode(LinkedNode node) {
        LinkedNode preNode = node.pre;
        LinkedNode nextNode = node.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    /**
     * 添加节点到头部
     *
     * @param node
     */
    private void addNode(LinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    /**
     * 将节点移动到头部
     *
     * @param node
     */
    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public int get(int key) {
        LinkedNode node = cache.get(key);
        if (node != null) {
            moveToHead(node);
        } else {
            return -1;
        }
        return node.value;
    }

    /**
     * 写入数据
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        LinkedNode node = cache.get(key);
        //存在
        if (node != null) {
            //可能更新数据
            node.value = value;
            moveToHead(node);
        }
        //不存在
        else {
            LinkedNode newNode = new LinkedNode();
            newNode.key = key;
            newNode.value = value;
            //更新Map
            cache.put(key, newNode);
            //添加结点到头部
            addNode(newNode);
            //更新结点数
            size++;
            //如果结点数超过容量大小
            if (size > capacity) {
                LinkedNode tailPre = tail.pre;
                //更新Map
                cache.remove(tailPre.key);
                //删除最后一个结点（尾结点的前一个结点）
                removeNode(tailPre);
                size--;
            }
        }
    }

    public static void main(String[] args) {
        // 缓存容量
        LruCache cache = new LruCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        // 返回  1
        cache.get(1);
        // 该操作会使得密钥 2 作废
        cache.put(3, 3);
        // 返回 -1 (未找到)
        cache.get(2);
        // 该操作会使得密钥 1 作废
        cache.put(4, 4);
        // 返回 -1 (未找到)
        cache.get(1);
        // 返回  3
        cache.get(3);
        // 返回  4
        cache.get(4);
    }
}
