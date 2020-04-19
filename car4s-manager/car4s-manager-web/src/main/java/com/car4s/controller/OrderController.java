package com.car4s.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    //获取预约信息列表
    @RequestMapping("/order/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows){
        EUDataGridResult result = orderService.getOrderList(page, rows);
        return result;
    }

    //删除预约信息
    @RequestMapping("/order/delete")
    @ResponseBody
    public Car4sResult delete(Long ids){
        String id = String.valueOf(ids);
        Car4sResult result = orderService.delete(id);
        return result;
    }

}
