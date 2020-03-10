package com.example.test.so;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by finup on 2020/3/9.
 */
public class VolatileTest {

    //保证可见性
    static volatile int num = 0;

    static AtomicInteger atomicInteger;

    public static void addNum(){
        num = 60;
    }

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("开始修改num的值");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNum();
            System.out.println("修改num的值成功");
        }).start();

        while (num == 0){
//            System.out.println("num == 0");
        }
        System.out.println("num == " + num);
    }

}
