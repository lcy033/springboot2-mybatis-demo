package com.example.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by finup on 2019/9/5.
 * 倒计时器示例:火箭发射
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch latch = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        //模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //计数减一
            //放在finally避免任务执行过程出现异常，导致countDown()不能被执行
            latch.countDown();
            System.out.println("当前" + latch.getCount());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i ++){
            executor.execute(demo);
        }

        // 等待检查
        latch.await();

        // 发射火箭
        System.out.println("Fire!");

        // 关闭线程
        executor.shutdown();

        HashMap map = new HashMap();
        map.put("s", "s");


        String s = "a";
        Map<String, String> map1 = new HashMap<>();
        if(!s.equals("")){
            map1.put("s", "s");
        }else {
            map1.put("s", "");
        }

        Optional.ofNullable(s).orElse("");

    }

}
