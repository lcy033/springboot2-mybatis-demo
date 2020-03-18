package com.example.test.lockCase;

/**
 * 死锁
 * Created by finup on 2020/3/18.
 */
public class DieLock {

    private String lockA;
    private String lockB;

    public DieLock(String lockA, String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    private void getLock(){
        synchronized (lockA){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获取资源A成功" + lockB);
            synchronized (lockB){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取资源B成功" + lockA);
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(() -> {
            DieLock dieLock = new DieLock(lockA, lockB);
            dieLock.getLock();
        }, "线程A").start();

        new Thread(() -> {
            DieLock dieLock = new DieLock(lockB, lockA);
            dieLock.getLock();
        }, "线程B").start();
    }
}
