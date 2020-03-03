package com.car4s.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.common.pojo.EUTreeNode;
import com.car4s.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    //查找广告栏节点
    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCategoryList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> categoryList = contentCategoryService.getCategoryList(parentId);
        return categoryList;
    }

    //新增分类广告栏节点
    @RequestMapping("/create")
    @ResponseBody
    public Car4sResult createContentCategory(long parentId, String name){
        Car4sResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }

    //删除广告栏节点
    @RequestMapping("/delete")
    @ResponseBody
    public Car4sResult deleteContentCategory(long id) {
        Car4sResult result = contentCategoryService.deleteContentCategory(id);
        return result;
    }

    //重命名广告栏节点
    @RequestMapping("/update")
    @ResponseBody
    public Car4sResult updateContentCategory(long id, String name) {
        Car4sResult result = contentCategoryService.updateContentCategory(id, name);
        return result;
    }
}
