package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


/**
 * redis 集群配置
 * @author finup
 * @date 2019/8/16
 */
//@Configuration
//@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfig {

    @Value("${spring.redis.password}")
    private String passWord;

    @Autowired
    ClusterConfigurationProperties clusterProperties;

    public @Bean RedisConnectionFactory connectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterProperties.getNodes());
        redisClusterConfiguration.setPassword(passWord);
        return new JedisConnectionFactory(redisClusterConfiguration);
    }
}
