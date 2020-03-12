package com.example.test.lockCase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * Created by finup on 2020/3/12.
 */
public class ReadWriteLock {

    private volatile Map<String, String> map = new HashMap<>();

    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 模拟写入
     * @param key
     * @param value
     */
    public void put(String key, String value){
        reentrantReadWriteLock.writeLock().lock();
        System.out.println("开始写入key:" + key + ",value:" + value);
        try {
            Thread.sleep(3000);
            map.put(key, value);
            System.out.println("写入完成key:" + key + ",value:" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }


    }

    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        System.out.println("开始获取key:" + key);
        try {
            Thread.sleep(3000);
            String value = map.get(key);
            System.out.println("获取完成key:" + key + ",value:" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();
        for (int i = 0; i < 5; i++) {
            Integer finalI = i;
            new Thread(()->{
                readWriteLock.put(finalI.toString(), finalI.toString());
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            Integer finalI = i;
            new Thread(()->{
                readWriteLock.get(finalI.toString());
            }).start();
        }
    }
}
