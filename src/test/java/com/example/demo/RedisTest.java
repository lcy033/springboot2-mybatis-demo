package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by finup on 2019/9/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void setRedis() throws Exception{

//        redisTemplate.opsForHash();  散列
//        redisTemplate.opsForList();  列表
//        redisTemplate.opsForSet();   集合
//        redisTemplate.opsForValue(); 字符串
//        redisTemplate.opsForZSet();  有序集合

        try {
            redisTemplate.opsForValue().set("aaaa", "bbbb");
        } catch (Exception e) {
            log.error("redis set放大异常,异常原因{}", e);
        }
    }
}
