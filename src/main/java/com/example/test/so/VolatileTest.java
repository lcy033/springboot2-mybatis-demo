package com.example.test.so;

/**
 * Created by finup on 2020/3/9.
 */
public class VolatileTest {

    static volatile int num = 0;

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
