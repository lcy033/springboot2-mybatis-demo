package com.example.synchronizedCase;

/**
 * Created by finup on 2019/8/9.
 */
public class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i< 5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "A" + i);
        }
    }
}
