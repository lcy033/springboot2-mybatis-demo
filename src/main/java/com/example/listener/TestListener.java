package com.example.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

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


    private static double getMoney(int remainSize, double remainMoney) {

        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (remainSize == 1) {
            return (double) Math.round(remainMoney * 100) / 100;
        }
        Random r     = new Random();
        double min   = 0.01; //
        double max   = remainMoney / remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        return money;
    }

    public static void main(String[] args) {
        double remainMoney = 100;
        for (int i = 2; i > 0; i--){
            double j =  getMoney(i, remainMoney);
            remainMoney = remainMoney - j;
            System.out.println("第" + i + "位，抢到" + j);
        }
    }
}
