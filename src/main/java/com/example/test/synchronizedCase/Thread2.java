package com.example.test.synchronizedCase;

/**
 * Created by finup on 2019/8/9.
 */
public class Thread2 extends Thread {

//    private String frs;
//    private String second;
//    public Thread2(String name, String frs, String second) {
//        super(name);
//        this.frs = frs;
//        this.second = second;
//    }

//    @Override
//    public void run() {
//        synchronized (frs) {
//            System.out.println(this.getName() + " obtained: " + frs);
//            try {
//                Thread.sleep(1000L);
//                synchronized (second) {
//                    System.out.println(this.getName() + " obtained: " + second);
//                }
//            } catch (InterruptedException e) { // Do nothing
//            }
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        String lockA = "lockA";
//        String lockB = "lockB";
//        Thread2 t1 = new Thread2("Thread1", lockA, lockB);
//        Thread2 t2 = new Thread2("Thread2", lockB, lockA);
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//    }

    @Override
    public void run() {
        Thread1 thread1 = new Thread1();
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 5; i< 10; i++){
            System.out.println(Thread.currentThread().getName() + "B" + i);
        }
    }

}
