package com.car4s.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.generator.pojo.TbItem;
import com.car4s.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 张少强
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    //根据id获得车辆
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.findById(itemId);
        return tbItem;
    }

    //获取车辆列表
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page,Integer rows){
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    //保存车辆
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public Car4sResult createItem(TbItem tbItem, String desc,String itemParams) throws Exception {
        Car4sResult result = itemService.createItem(tbItem, desc, itemParams);
        return  result;
    }

    //删除车辆
    @RequestMapping("/item/delete")
    @ResponseBody
    public Car4sResult delete(Long ids){
            Car4sResult result = itemService.delete(ids);
            return Car4sResult.ok();
    }
}
