package com.car4s.portal.service;

import com.car4s.generator.pojo.TbUser;

/**
 * Created by 张少强
 */
public interface UserService {
    /**
     * 调用服务获取用户信息
     * @param token
     * @return
     */
    TbUser getUserByToken(String token);

}
