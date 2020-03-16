package com.example.test.QueueCase;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 堵塞队列 一共有七种
 * Created by finup on 2020/3/16.
 */
public class BlockingQueueCase {

    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    private BlockingQueueCase(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产者
     * @throws Exception
     */
    private void myPrvider() throws Exception{
        String date;
        while (FLAG){
            date = atomicInteger.incrementAndGet() + "";
            if (!blockingQueue.offer(date, 2L, TimeUnit.SECONDS)){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "生产数据超过2秒退出");
                return;
            }else {
                System.out.println(Thread.currentThread().getName() + "生产数据:" + date);
            }
            TimeUnit.SECONDS.sleep(1L);
        }
    }

    /**
     * 消费者
     * @throws Exception
     */
    private void myConsumer() throws Exception {
        String result;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null){
                if (FLAG){
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "消费数据超过2秒退出");
                    return;
                }
            }else {
                System.out.println(Thread.currentThread().getName() + "消费数据:" + result);
            }

        }
    }

    /**
     * 停止
     * @throws Exception
     */
    private void myStop() {
        System.out.println("----stop----");
        FLAG = false;
    }

    public static void main(String[] args) {
        BlockingQueueCase blockingQueueCase = new BlockingQueueCase(new ArrayBlockingQueue(10));
        new Thread(() -> {
            try {
                blockingQueueCase.myPrvider();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                blockingQueueCase.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        blockingQueueCase.myStop();
    }


//    public static void main(String[] args) {
//        //数组加堵塞队列
//        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(3);
//        //add remote 队列数据不够的时候报错、offer（可以加超时） 不够了返回false poll没有数据的时候返回null、put 数据长度不够堵塞 take 数据不够的时候堵塞
//        System.out.println(arrayBlockingQueue.add("a"));
//        System.out.println(arrayBlockingQueue.add("b"));
//        System.out.println(arrayBlockingQueue.add("c"));
//        System.out.println(arrayBlockingQueue.element());
//        System.out.println(arrayBlockingQueue.remove());
//        //永远只有一个队列（不消费不生产）
//        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
//    }

}
