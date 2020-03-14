package com.car4s.sso.service.impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.CookieUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.mapper.TbUserMapper;
import com.car4s.generator.pojo.TbUser;
import com.car4s.generator.pojo.TbUserExample;
import com.car4s.sso.dao.JedisClient;
import com.car4s.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 张少强
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Long  SSO_SESSION_EXPIRE;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public Car4sResult checkData(String content, Integer type) {
        //对数据进行校验: 1、username 2、phone 3、email
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        if (1 == type) {
            criteria.andUsernameEqualTo(content);
        } else if (2 == type) {
            criteria.andPhoneEqualTo(content);
        } else {
            criteria.andEmailEqualTo(content);
        }
        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        if( list ==null || list.size() == 0 ){
            return Car4sResult.ok(true);
        }
        return Car4sResult.ok(false);
    }

    @Override
    public Car4sResult createUser(TbUser tbUser) {
        tbUser.setUpdated(new Date());
        tbUser.setCreated(new Date());
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        tbUserMapper.insert(tbUser);
        return Car4sResult.ok();
    }

    @Override
    public Car4sResult userLogin(String username, String password, HttpServletRequest requsest, HttpServletResponse response) {
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        if(list == null || list.size() ==0){
            return Car4sResult.build(400,"用户名密码错误");
        }
        TbUser tbUser = list.get(0);
        if( !DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())){
            return Car4sResult.build(400,"用户名密码错误");
        }
        String token = UUID.randomUUID().toString();
        tbUser.setPassword(null);
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtil.objectToJson(tbUser));
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,  SSO_SESSION_EXPIRE);
        CookieUtil.setCookie(requsest,response,"TT_TOKEN",token);
        return Car4sResult.ok(token);
    }

    @Override
    public Car4sResult getUserByToken(String token) {
        //根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        //判断是否为空
        if (StringUtils.isBlank(json)) {
            return Car4sResult.build(400, "此session已经过期，请重新登录");
        }
        //更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        //返回用户信息
        return Car4sResult.ok(JsonUtil.jsonToPojo(json, TbUser.class));
    }

    @Override
    public Car4sResult outLogin(String token) {
        try {
            long del = jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(400, "删除token出错");
        }
        return Car4sResult.ok();
    }
}
