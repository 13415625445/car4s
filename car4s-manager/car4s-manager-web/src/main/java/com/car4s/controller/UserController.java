package com.car4s.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Car4sResult login(String name, String password){
        try {
            Car4sResult result = userService.login(name, password);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/show")
    public String show(){
        return "backstage";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "index";
    }
}
