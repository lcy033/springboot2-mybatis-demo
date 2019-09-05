package com.example.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by finup on 2019/9/5.
 */
public class CyclicBarrierTaskDemo extends Thread{

    CyclicBarrier cyclicBarrier;

    public CyclicBarrierTaskDemo(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(getName() + " 到达栅栏 A");
            cyclicBarrier.await();
            System.out.println(getName() + " 冲破栅栏 A");

//            Thread.sleep(2000);
//            System.out.println(getName() + " 到达栅栏 B");
//            cyclicBarrier.await();
//            System.out.println(getName() + " 冲破栅栏 B");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println(Thread.currentThread().getName() + "完成最后的任务"));

        for (int i = 0; i < 5; i++){
            new CyclicBarrierTaskDemo(barrier).start();
        }

        Thread.sleep(5000);

    }
}
