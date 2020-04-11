package com.car4s.order.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.generator.pojo.TbUser;
import com.car4s.order.pojo.Order;
import com.car4s.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张少强
 */
@Controller
public class orderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public Car4sResult createOrder(@RequestBody Order order){
        try {
            Car4sResult result = orderService.createOrder(order, order.getOrderItems());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/show")
    @ResponseBody
    public Car4sResult showOrder(@RequestBody TbUser user){
        try {
            Car4sResult result = orderService.showOrder(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
