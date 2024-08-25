package com.hao.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(1000));
        // 创建连接池对象
        jedisPool = new JedisPool(jedisPoolConfig,
                "192.168.128.131", 6379, 1000, "031023");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
