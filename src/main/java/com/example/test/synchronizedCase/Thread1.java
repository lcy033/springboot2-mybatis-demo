package com.example.test.synchronizedCase;

/**
 * Created by finup on 2019/8/9.
 */
public class Thread1 extends Thread {
    @Override
    public void run() {

        Thread2 thread2 = new Thread2();
        thread2.start();
        for (int i = 1; i< 5; i++){
            try {
                thread2.join();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "A" + i);
        }
    }
}
