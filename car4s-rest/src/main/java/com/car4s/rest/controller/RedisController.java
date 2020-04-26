package com.car4s.rest.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
    @Autowired
    private RedisService redisService;

    //删除内容缓存
    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public Car4sResult contentCacheSync(@PathVariable Long contentCid){
        Car4sResult result = redisService.syncContent(contentCid);
        return result;
    }
}
