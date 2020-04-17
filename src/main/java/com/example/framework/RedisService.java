package com.example.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis通用处理类
 */
@Component
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 构造方法
     */
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 构造方法
     */
    public RedisService() {
        super();

    }


    /**
     * 存储string类型并且需要设置失效时间
     */
    public void expire(String key, String value, long expireTime, TimeUnit timeUnit) {
        this.setString(key, value);
        redisTemplate.expire(key, expireTime, timeUnit);
    }

    /**
     * 存储string类型并且需要设置失效时间
     */
    public void expire(String key, String value, long expireTime) {
        this.setString(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 遍历key
     */
    public Set scan(String key) {
        Set<String> keys = redisTemplate.keys(key);
        return keys;
    }

    /**
     * 删除key
     */
    public void delSet(Set keys) {
        redisTemplate.opsForValue().getOperations().delete(keys);
    }

    /**
     * 设置失效时间
     */
    public void expire(String key, long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 存储string类型
     */
    public void setString(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("redis set放大异常,异常原因{}", e);
        }
    }

    public void setStringExpire(String key, String value, long timeSeconds) {
        try {
            redisTemplate.opsForValue().set(key, value, timeSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("redis set放大异常,异常原因{}", e);
        }
    }

    /**
     * 获取结果(String 类型)
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Long getIncrement(String key) {
        return redisTemplate.boundValueOps(key).increment(0);
    }

    /**
     * 取set结合
     */
    public Set<String> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 添加set集合
     */
    public void addForSet(String key, String... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    /**
     * pop for set
     */
    public String popForSet(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * set集合中个数
     */
    public Long sizeForSet(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * del
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 添加 哈希表元素
     */
    public void addForHash(String key, Map<String, String> stringStringMap) {
        redisTemplate.opsForHash().putAll(key, stringStringMap);
    }

    /**
     * 删除 哈希表元素
     */
    public void delForHash(String key, String... objects) {
        redisTemplate.opsForHash().delete(key, objects);
    }

    /**
     * 自增长
     */
    public Long increment(String key) {
        return redisTemplate.boundValueOps(key).increment(1);
    }

    /**
     * 设置hash属性自增计数
     *
     * @param key    hash key
     * @param hkey   hash属性
     * @param expire hash过期时间 秒
     */
    public void incrementHash(String key, String hkey, long expire) {
        this.expire(key, expire);
        redisTemplate.opsForHash().increment(key, hkey, 1);
    }

    /**
     * 返回key中属性map集合
     *
     * @param key
     * @return
     */
    public Map<Object, Object> getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 自增长 设置失效时间
     */
    public Long increment(String key, long expireTime) {
        Long redisValue = this.increment(key);
        if (redisValue == 1) {
            this.expire(key, expireTime);
        }
        return redisValue;
    }

    /**
     * 获取list集合中范围数据
     */
    public List<String> lRange(String key, long startElement, long endElement) {
        return redisTemplate.boundListOps(key).range(startElement, endElement);
    }

    /**
     * 添加指定key集合中添加元素 先进后出原则   
     */
    public Long leftPush(String key, String params) {
        Long lPushResult = 0L;
        try {
            lPushResult = redisTemplate.boundListOps(key).leftPush(params);
        } catch (Exception e) {
            logger.error("redis leftPush异常,异常原因{}", e);
        }
        return lPushResult;
    }
    public Long listLenth(String key) {
        Long length = 0L;
        try {
            length = redisTemplate.boundListOps(key).size();
        } catch (Exception e) {
            logger.error("redis leftPush异常,异常原因{}", e);
        }
        return length;
    }
    /**
     * 删除list中指定元素
     */
    public Long lrem(String key, long count, String params) {
        return redisTemplate.opsForList().remove(key, count, params);

    }

    /**
     * 将 key 的值设为 value, 仅当 key 不存在。
     * 若给定的 key 已经存在，则 SETNX 不做任何动作
     *
     * @param key   key
     * @param value value
     * @return boolean
     */
    public boolean setnx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }
}
