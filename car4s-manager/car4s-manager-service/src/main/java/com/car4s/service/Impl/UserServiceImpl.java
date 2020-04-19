package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.mapper.TbManagerMapper;
import com.car4s.generator.pojo.TbManager;
import com.car4s.generator.pojo.TbManagerExample;
import com.car4s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbManagerMapper tbManagerMapper;

    @Override
    public Car4sResult login(String name, String password) {
        TbManagerExample tbManagerExample = new TbManagerExample();
        TbManagerExample.Criteria criteria = tbManagerExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<TbManager> list = tbManagerMapper.selectByExample(tbManagerExample);
        if(list == null || list.size() ==0){
            return Car4sResult.build(400,"用户名密码错误");
        }
        TbManager tbManager = list.get(0);
        if(password.equals(tbManager.getPassword())){
            return Car4sResult.ok();
        }else {
            return Car4sResult.build(400, "用户名或者密码错误！，请重新输入");
        }
    }
}
