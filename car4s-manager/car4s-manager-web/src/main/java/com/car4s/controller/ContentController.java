package com.car4s.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.generator.pojo.TbContent;
import com.car4s.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    //获取分页内容分内列表
    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getContentCategoryList(long categoryId,Integer page, Integer rows){
        EUDataGridResult contentCategoryList = contentService.getContentCategoryList(categoryId, page, rows);
        return contentCategoryList;
    }

    //增加内容
    @RequestMapping("/save")
    @ResponseBody
    public Car4sResult addContent(TbContent tbContent){
        Car4sResult result = contentService.addContent(tbContent);
        return result;
    }

    //更新内容
    @RequestMapping("/update")
    @ResponseBody
    public Car4sResult updateContent(TbContent tbContent){
        Car4sResult result = contentService.updateContent(tbContent);
        return result;
    }
    //删除内容
    @RequestMapping("/delete")
    @ResponseBody
    public Car4sResult deleteContent(@RequestParam(value = "ids") long id){
        Car4sResult result = contentService.deleteContent(id);
        return result;
    }
}
