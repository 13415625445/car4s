package com.car4s.rest.service;

import com.car4s.common.pojo.Car4sResult;

/**
 * Created by 张少强
 */
public interface RedisService {
    Car4sResult syncContent(long cotentCid);
}
