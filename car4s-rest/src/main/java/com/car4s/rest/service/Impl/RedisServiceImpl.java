package com.car4s.rest.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.rest.dao.JedisClient;
import com.car4s.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by 张少强
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public Car4sResult syncContent(long cotentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, cotentCid + "");
        } catch (Exception e) {
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return Car4sResult.ok();
    }
}
