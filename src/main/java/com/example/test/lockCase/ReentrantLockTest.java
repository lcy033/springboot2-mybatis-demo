package com.example.test.lockCase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 采用ReentrantLock 实现 生产者消费者模式
 * Created by finup on 2020/3/16.
 */
public class ReentrantLockTest {

    private volatile int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void add() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num = 1;
            System.out.println(Thread.currentThread().getName() + "生产：" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void get() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num = 0;
            System.out.println(Thread.currentThread().getName() + "消费：" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                reentrantLockTest.add();
            }, "A").start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                reentrantLockTest.get();
            }, "B").start();
        }


    }

}
