package com.example.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by finup on 2019/8/30.
 */
public class LockTest implements Runnable{

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        lock.lock();
        for (int i = 0; i < 5; i++){
            System.out.println("当前线程名：" + Thread.currentThread().getName() + ",i=" + i);
        }
        lock.unlock();
        lock.unlock();
    }

    public static void main(String[] args) {
        LockTest lockTest =  new LockTest();
        Thread thread1 = new Thread(lockTest);
        Thread thread2 = new Thread(lockTest);
        Thread thread3 = new Thread(lockTest);
        thread1.start();
        thread2.start();
        thread3.start();

    }

}
