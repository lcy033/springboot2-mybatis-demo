package com.example.test.volatileCase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by finup on 2020/3/11.
 */
public class ABACase {

    //int 范围 -128 到 127  超过 就不准了
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(128);

    static AtomicInteger atomicInteger = new AtomicInteger(187);

    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(111, 1);

    public static void main(String[] args) {

        System.out.println(atomicInteger.compareAndSet(187, 193) + "int" + atomicInteger.get());

        int stamp = 1;

        new Thread(()->{
            System.out.println(atomicReference.compareAndSet(128, 132) + "第一次" + atomicReference.get());
            System.out.println(atomicReference.compareAndSet(132, 128) + "第二次" + atomicReference.get());

            System.out.println(atomicStampedReference.compareAndSet(111, 112, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1) + "ABA第一次" + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(112, 111, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1) + "ABA第二次" + atomicStampedReference.getStamp());

        }, "t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicReference.compareAndSet(128, 2020) + "第三次" + atomicReference.get());

            System.out.println(atomicStampedReference.compareAndSet(111, 2021, stamp, stamp + 1) + "ABA第三次" + atomicStampedReference.getStamp());

        }, "t2").start();
    }

}
