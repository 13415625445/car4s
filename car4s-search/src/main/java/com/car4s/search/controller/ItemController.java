package com.car4s.search.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //导入全部车辆进入索引
    @RequestMapping("/importAllItems")
    @ResponseBody
    public Car4sResult importAllItems(){
        Car4sResult result = null;
        try {
            result = itemService.importAllItems();
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;

    }
}
