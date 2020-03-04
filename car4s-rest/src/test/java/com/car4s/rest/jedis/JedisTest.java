package com.car4s.rest.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by 张少强
 */


public class JedisTest {
    @Test
    public void JedisTest(){
        Jedis jedis = new Jedis("192.168.117.131", 6379);
        jedis.set("key1", "jedis test");
        String key1 = jedis.get("key1");
        System.out.println(key1);
        jedis.close();
    }

    @Test
    public void JedisPoo(){
        JedisPool jedisPool = new JedisPool("192.168.117.131", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("key2","haahha");
        String key2 = jedis.get("key2");
        System.out.println(key2);
        jedis.close();
    }
}
