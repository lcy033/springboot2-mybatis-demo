package com.example.threadPollExecutorCase;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by finup on 2019/8/12.
 */
public class ThreadTest {

    public static void main(String[] args) {
        int corePoolSize = 4;
        int maximumPoolSize = 5;
        long keepAliveTime = 30;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }


//        int corePoolSize = 5;
//        int maximumPoolSize = 10;
//        long keepAliveTime = 30;
//        TimeUnit unit = TimeUnit.SECONDS;
//        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
//        ThreadFactory threadFactory = new ThreadPoolTaskExecutor();
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
//                workQueue, threadFactory, handler);
//        executor.prestartAllCoreThreads(); // 预启动所有核心线程
//
//        executor.execute(()-> {
//            for (int i = 1; i < 10; i++) {
//                int finalI = i;
//                System.out.println(finalI);
//            }
//        });


        new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    int finalI = i;
                    System.out.println(finalI);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            }.run();
        }



        try {
            System.in.read(); //阻塞主线程
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
