package com.example.demo;

import com.example.framework.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * Created by finup on 2019/9/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisService redisService;

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

    @Test
    public void getRedis() {
        System.out.println(redisService.get("a"));
        redisService.del("mylist");
    }

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);//设置地址和端口
        jedis.auth("test123");//如果redis服务器配置了需要密码，此处必须设置

        //存储集合到redis，并取出
        jedis.lpush("mylist","admin","tom","jack");

        System.out.print(jedis.lrange("mylist",0,-1));
    }

}
