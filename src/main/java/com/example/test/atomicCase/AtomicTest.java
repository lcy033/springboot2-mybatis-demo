package com.example.test.atomicCase;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * Created by finup on 2020/3/12.
 */
public class AtomicTest {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //安全list
//    CopyOnWriteArrayList list = new CopyOnWriteArrayList();


    private void myLock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)){
            System.out.println(thread.getName() + "获取锁失败，尝试获取。");
        }
        System.out.println(thread.getName() + "获取锁成功。");
//        this.myUnLock();
    }

    private void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "解锁成功");
    }

    public static void main(String[] args) {

        AtomicTest atomicTest = new AtomicTest();
        new Thread(()->{
            atomicTest.myLock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                atomicTest.myUnLock();
            }
        },"t1").start();

        new Thread(()->{
            atomicTest.myLock();
            atomicTest.myUnLock();
        },"t2").start();


    }
}
