package com.car4s.controller;

import com.car4s.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 张少强
 */
@Controller
public class ItemParamItemController {
    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/show/{itemId}")
    public String showItemParamItem(@PathVariable Long itemId, Model model){
        String itemParamByItemId = itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("itemParamItem",itemParamByItemId);
        return "item";
    }
}
