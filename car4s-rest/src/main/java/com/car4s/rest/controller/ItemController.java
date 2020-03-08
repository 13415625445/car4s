package com.car4s.rest.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //获取基本信息
    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public Car4sResult getItemBaseInfo(@PathVariable Long itemId) {
        Car4sResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }

    //获取描述信息
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public Car4sResult getItemDescInfo(@PathVariable Long itemId) {
        Car4sResult result = itemService.getItemDescInfo(itemId);
        return result;
    }

    //获取规格信息
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public Car4sResult getItemParamInfo(@PathVariable Long itemId) {
        Car4sResult result = itemService.getItemParamInfo(itemId);
        return result;
    }

}
