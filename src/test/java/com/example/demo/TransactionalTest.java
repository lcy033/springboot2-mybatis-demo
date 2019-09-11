package com.example.demo;

import com.example.service.GspMenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by finup on 2019/9/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransactionalTest {

    @Autowired
    private GspMenuService gspMenuService;

    @Test
    public void test1() throws Exception{
        gspMenuService.addGspMenu();
    }
}
