package com.car4s.rest.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

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

    @Test
    public void JedisCluser(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.117.131",7001));
        nodes.add(new HostAndPort("192.168.117.131",7002));
        nodes.add(new HostAndPort("192.168.117.131",7003));
        nodes.add(new HostAndPort("192.168.117.131",7004));
        nodes.add(new HostAndPort("192.168.117.131",7005));
        nodes.add(new HostAndPort("192.168.117.131",7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key", "傻逼罗尖锐");
        String key = cluster.get("key");
        System.out.println(key);

        cluster.close();
    }

    @Test
    public void JedisCluster(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster redisClient = (JedisCluster) applicationContext.getBean("redisClient");
        String key = redisClient.get("key");
        System.out.println(key);
        redisClient.close();

    }
}
