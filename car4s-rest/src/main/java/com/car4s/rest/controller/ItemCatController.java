package com.car4s.rest.controller;

import com.car4s.common.utils.JsonUtil;
import com.car4s.rest.pojo.CatResult;
import com.car4s.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

/**
 * Created by 张少强
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    //获取汽车分类信息
    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
