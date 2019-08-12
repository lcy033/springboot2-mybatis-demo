package com.example.synchronizedCase;

/**
 * Created by finup on 2019/8/9.
 */
public class Thread2 extends Thread {
    @Override
    public void run() {
        Thread1 thread1 = new Thread1();
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 5; i< 10; i++){
            System.out.println(Thread.currentThread().getName() + "B" + i);
        }
    }

}
