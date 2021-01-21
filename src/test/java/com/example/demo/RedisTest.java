package com.example.demo;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.example.framework.RedisService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.util.ArrayList;

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

//    @Autowired
//    private RedissonClient redisson;

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
        redisService.setString("a", "bbb");
        System.out.println(redisService.get("a"));
        redisService.del("a");
    }

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("172.16.12.252", 6379);//设置地址和端口
//        jedis.auth("test123");//如果redis服务器配置了需要密码，此处必须设置

//        Jedis jedis = new Jedis("r-2zebe1v34r5ocbaoih.redis.rds.aliyuncs.com", 6379);//设置地址和端口
//        jedis.auth("oHUr#^VvWRLkI9rv");//如果redis服务器配置了需要密码，此处必须设置
        jedis.set("aaa", "bbbb");

        System.out.println(jedis.get("aaa"));

        jedis.del("aaa");

        System.out.println(jedis.get("aaaa"));

        //存储集合到redis，并取出
//        jedis.lpush("mylist","admin","tom","jack");
//
//        System.out.print(jedis.lrange("mylist",0,-1));
    }

//    @Bean
//    public RedissonClient redisson() {
////        RedissonClient redisson = Redisson.create(
////                Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream()));
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://172.0.0.1:6379").setPassword("test123").setDatabase(0);
//        return Redisson.create(config);
//    }
//
//    @Test
//    public void redisson() {
//        String userId = "1";
//        String key = userId + "_key";
//        //获取锁
//        RLock lock = redisson.getLock(key);
//        lock.lock();
//        //执行具体逻辑...
//
//        RBucket<Object> bucket = redisson.getBucket("a");
//        bucket.set("bb");
//
//        lock.unlock();
//    }

    @Test
    public void shouldAnswerWithTrue() {
        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://10.16.12.252:3310/gsvip_role?characterEncoding=UTF-8");
        hikariConfig.setUsername("aitifen");
        hikariConfig.setPassword("aitifen");
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir("/Users/finup/Desktop")
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.HTML).customTemplate("")
                //生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        //忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("test_user");
        ignoreTableName.add("test_group");
        //忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");
        //忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");
        ProcessConfig processConfig = ProcessConfig.builder()
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("数据库设计文档生成")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig).build();
        //执行生成
        new DocumentationExecute(config).execute();
    }

}
