package com.car4s.sso.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 张少强
 */
public interface UserService {
    /**
     * 数据校验(注册）
     */
    Car4sResult checkData(String content, Integer type);

    /**
     * 用户注册
     */
    Car4sResult createUser(TbUser tbUser);

    /**
     * 用户登录
     */
    Car4sResult userLogin(String username, String password, HttpServletRequest requsest, HttpServletResponse response);

    /**
     * 根据token获取用户信息
     */
    Car4sResult getUserByToken(String token);

    /**
     * 登出
     */
    Car4sResult outLogin(String token);
}
