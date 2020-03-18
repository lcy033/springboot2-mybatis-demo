package com.example.test.synchronizedCase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by finup on 2019/8/6.
 */
public class Case1 extends Thread {

    private final int number = 66;

    private final static Byte[] locks = new Byte[0];

    private Case1(int num){
        this.getNum(num);
    }

    /**
     * 普通同步方法，锁是当前实例对象
     */
    private synchronized void getNum(int num){
        for (int i = 0; i < num; i++){
            System.out.println(Thread.currentThread().getName() + "普通-" + i);
        }
    }

    /**
     * 静态同步方法，锁是当前类的class对象；
     */
    private static synchronized void getNum1(){
        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + "静态-" + i);
        }
    }

    /**
     * 同步方法块，锁是括号里面的对象。
     */
    private void getNum2(){
        synchronized (locks){
            for (int i = 0; i < 10; i++){
                System.out.println("方法块-" + i);
            }
        }
    }

    public void newThreadExecutorService(){
        //自定义线程个数 核心线程数与最大线程数相等，说明都是核心线程是其优势
        //keepAliveTime 0 该参数默认对核心线程无效
        //LinkedBlockingQueue 无界阻塞队列 队列为最大值 如果提交任务的速度大于处理的任务会造成队列大量堵塞
        //因为队列很大，很有可能在拒绝策略前，内存溢出。是其劣势
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
//        threadPoolExecutor.setCorePoolSize(2);
        //适用场景：可用于Web服务瞬时削峰，但需注意长时间持续高峰情况造成的队列阻塞。

        //创建单个线程的线程池 核心线程与最大线程数相等都是1个
        //keepAliveTime 0 该参数默认对核心线程无效
        //LinkedBlockingQueue 无界阻塞队列 队列为最大值 如果提交任务的速度大于处理的任务会造成队列大量堵塞
        //因为队列很大，很有可能在拒绝策略前，内存溢出。是其劣势
        //与 newFixedThreadPool 线程池相比 newSingleThreadExecutor 每次结束以后会自动 shutdown
        //被定义后无法修改
//        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //运行时异常 ClassCastException
//        ThreadPoolExecutor threadPoolExecutor1 = (ThreadPoolExecutor) executorService1;
//        threadPoolExecutor1.setCorePoolSize(3);

        //核心线程数0、最大线程数无限 每个任务都去创建线程处理
        //线程空闲60s后自动结束
        //workQueue 采用 SynchronousQueue 同步队列，入队出队必须同时进行
        //因为线程数创建无限制 所以采用 SynchronousQueue（内部也是BlockingQueue） 无需等待 接力棒
//        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //适用场景：快速处理大量耗时较短的任务，如Netty的NIO接受请求时，可使用CachedThreadPool。

        //newScheduledThreadPool调用的是ScheduledThreadPoolExecutor的构造方法，而ScheduledThreadPoolExecutor继承了ThreadPoolExecutor，构造是还是调用了其父类的构造方法。
//        ExecutorService executorService3 = Executors.newScheduledThreadPool();
    }

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 1; i < 3; i++){
////            executorService.execute(() -> {
////                for (int j = 0; j < 10; j++){
////                    System.out.println(Thread.currentThread().getName() + "普通-" + j);
//////                    Case1.getNum1();
////                }
////            });
//            Thread1 thread1 = new Thread1();
//            executorService.execute(thread1);
//        }
//        executorService.shutdown();

//        Thread1 thread1 = new Thread1();
//        thread1.start();

        Thread2 thread2 = new Thread2();
        thread2.start();

    }

}
