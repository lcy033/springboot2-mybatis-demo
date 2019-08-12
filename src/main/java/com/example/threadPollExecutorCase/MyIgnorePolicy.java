package com.example.threadPollExecutorCase;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by finup on 2019/8/12.
 */
public class MyIgnorePolicy implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        doLog(r, executor);
    }

    private void doLog(Runnable r, ThreadPoolExecutor e) {
        // 可做日志记录等
        System.err.println(r.toString() + " rejected");
        System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
    }
}
