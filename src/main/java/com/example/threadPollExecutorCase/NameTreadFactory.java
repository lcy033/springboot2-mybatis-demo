package com.example.threadPollExecutorCase;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by finup on 2019/8/12.
 */
public class NameTreadFactory implements ThreadFactory {

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
        System.out.println(t.getName() + " has been created");
        return t;
    }
}
