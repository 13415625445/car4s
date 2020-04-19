package com.car4s.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.generator.pojo.TbItemParam;
import com.car4s.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("query/itemcatid/{cid}")
    public Car4sResult getItemParamByCid(@PathVariable Long cid) throws Exception {
        //根据分类id查询列表
        Car4sResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }

    //获取车辆列表
    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows){
        EUDataGridResult result = itemParamService.getItemParamList(page, rows);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public Car4sResult insertItemParam(@PathVariable Long cid, String paramData){
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        Car4sResult result = itemParamService.insertItemParam(tbItemParam);
        return result;
    }

    @RequestMapping("/delete")
    public Car4sResult delete(Long ids){
        Car4sResult result = itemParamService.delete(ids);
        return result;
    }
}
