package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by finup on 2019/8/16.
 */
//@Configuration
public class RabbitConfig {

    @Bean
    public Queue testQueue(){
        return new Queue("queue.test.new.test");
    }

}
