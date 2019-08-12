package com.example.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * Created by finup on 2018/12/21. 线程池
 */
@Configuration
@EnableAsync
public class TreadPoolConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreadPoolConfig.class);

    /**
     * 消费队列线程
     */
    @Bean
    public ExecutorService buildConsumerQueueThreadPool() {
        LOGGER.info("start buildConsumerQueueThreadPool");
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-queue-thread-%d").build();
        LOGGER.info("end buildConsumerQueueThreadPool");
        return new ThreadPoolExecutor(5, 10, 60L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }
}
