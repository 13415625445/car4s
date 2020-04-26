package com.car4s.rest.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.generator.pojo.TbContent;
import com.car4s.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 张少强
 */
@RequestMapping("/content")
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    //根据内容分类ID获取内容（目前为广告）
    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public Car4sResult getContentList(@PathVariable Long contentCategoryId){
        try {
            List<TbContent> contentList = contentService.getContentList(contentCategoryId);
            return Car4sResult.ok(contentList);
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
