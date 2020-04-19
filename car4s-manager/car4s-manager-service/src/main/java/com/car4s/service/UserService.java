package com.car4s.service;

import com.car4s.common.pojo.Car4sResult;

/**
 * Created by 张少强
 */
public interface UserService {
    Car4sResult login(String name, String password);
}
