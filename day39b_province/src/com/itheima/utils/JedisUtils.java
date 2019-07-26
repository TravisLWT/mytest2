package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis工具类
 * 1.保证Jedis连接池一个
 * 2.提供Jedis
 * 3.释放资源
 */
public class JedisUtils {

    private static JedisPoolConfig jedisPoolConfig;
    private static String host = "localhost";
    private static int port = 6379;
    private static JedisPool jedisPool;

    static {

        //1.创建jedis池子配置对象
        jedisPoolConfig = new JedisPoolConfig();
        //2.创建jedis池子对象
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    /**
     * 提供Jedis
     * @return
     */
    public static Jedis getJedis() {
        //3.从池子里面获得jedis
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 释放资源
     * @param jedis
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
