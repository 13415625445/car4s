package com.car4s.controller;

import com.car4s.common.pojo.EUTreeNode;
import com.car4s.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by 张少强
 */

/**
 * 查找分类列表
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> euTreeNodes = itemCatService.getItemCatList(parentId);
        return euTreeNodes;
    }
}
