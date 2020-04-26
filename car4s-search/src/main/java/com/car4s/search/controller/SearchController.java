package com.car4s.search.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.search.pojo.SearchResult;
import com.car4s.search.service.ItemSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
public class SearchController {

    @Autowired
    private ItemSearchService itemSearchService;

    //根据所给关键字进行搜索（缺点：只要符合其中一个字就识别为所要的车辆，正确性需要调整）
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Car4sResult search(@RequestParam("q")String queryString, @RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "60")Integer rows){
        if(StringUtils.isBlank(queryString)){
            return Car4sResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
           searchResult = itemSearchService.searchItem(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return Car4sResult.ok(searchResult);
    }
}
