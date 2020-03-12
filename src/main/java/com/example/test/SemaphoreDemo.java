package com.example.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by finup on 2019/10/9.
 */
@Slf4j
public class SemaphoreDemo {

    /**
     * 请求数量
     */
    private static final int THREAD_COUNT = 20;

    /**
     * 模拟
     * @param threadnum
     * @throws InterruptedException
     */
    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        log.info("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }

    public static void main(String[] args) {

        //创建线程池（简单）
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        //设置可以同时通过的数量(也可以设置模式，公平、非公平，默认是非公平模式false)
        final Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {
                try {
                    //获取许可
                    semaphore.acquire();
                    log.info("threadnum:" + threadnum + "获取许可");
                    test(threadnum);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally {
                    //释放许可
                    semaphore.release();
                    log.info("threadnum:" + threadnum + "释放许可");
                }
            });
        }
        threadPool.shutdown();
        log.info("完成");
    }

}
