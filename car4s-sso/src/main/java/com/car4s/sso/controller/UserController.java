package com.car4s.sso.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.generator.pojo.TbUser;
import com.car4s.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        Car4sResult result = null;
        if (StringUtils.isBlank(param)) {
            result = Car4sResult.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = Car4sResult.build(400, "检验内容不能为空");
        }
        if (type != 1 && type != 2 && type != 3) {
            result = Car4sResult.build(400, "校验内容类型错误");
        }
        if (null != result) {
            if (null != callback) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        }
        try {
            result = userService.checkData(param, type);
        } catch (Exception e) {
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if (null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    //创建用户
    @RequestMapping("/register")
    @ResponseBody
    public Car4sResult createUser(TbUser user) {

        try {
            Car4sResult result = userService.createUser(user);
            return result;
        } catch (Exception e) {
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    //用户登录
    @RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public Car4sResult userLogin(String username, String password, HttpServletRequest requsest, HttpServletResponse response) {
        try {
            Car4sResult result = userService.userLogin(username, password, requsest, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    //根据token在redis获取用户信息
    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        Car4sResult result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        //判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            return result;
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }

    //根据token删除redis里面的用户信息
    @RequestMapping("/logout/{token}")
    @ResponseBody
    public Object outLoginByToken(@PathVariable String token, String callback) {
        Car4sResult result = null;
        try {
            result = userService.outLogin(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if (!StringUtils.isBlank(callback)){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }
}
