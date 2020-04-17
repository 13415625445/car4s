package com.car4s.portal.controller;

import com.car4s.common.utils.JsonUtil;
import com.car4s.portal.pojo.ContrastItem;
import com.car4s.portal.pojo.ItemInfo;
import com.car4s.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/contrast")
public class ContrastController {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value="/item/{itemId}",produces = "text/html; charset=utf-8")
    public String showItemById(@PathVariable Long itemId) {
        ContrastItem item1 = itemService.contrastItemById(itemId);
        String json = JsonUtil.objectToJson(item1);
        return json;
    }

    @RequestMapping("/show")
    public String showItem(Model model) {
        List<ItemInfo> list = itemService.getItemList();
        model.addAttribute("list",list);
        return "contrast";
    }
}
