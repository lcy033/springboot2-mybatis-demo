package com.example.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by finup on 2019/8/16.
 */
@Slf4j
@Component
public class TestListener {

    @RabbitHandler
    @RabbitListener(queues = "queue.test.new.test")
    public void queueTest(Message message){
        log.info(JSON.toJSONString("接收到消息:" + message));
    }
}
