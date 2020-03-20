package com.car4s.order.dao;

/**
 * Created by 张少强
 */
public interface JedisClient {

    String get(String key);
    String set(String key, String value);
    String hget(String hkey, String key);
    Long hset(String hkey, String key, String value);
    long incr(String key);
    long expire(String key, long second);
    long ttl(String key);
    long del(String key);
    long hdel(String hkey, String key);

}
