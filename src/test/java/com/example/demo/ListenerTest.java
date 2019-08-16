package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by finup on 2019/8/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ListenerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sender() throws Exception{
        String message = "hello word";
        log.info("发送消息：" + message);
        rabbitTemplate.convertAndSend("queue.test.new.test", message);
        TimeUnit.SECONDS.sleep(60);
    }

}
